import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

public class Search2
{
  private static final int CLOSED_MAX_SIZE = 100000;
  private static final int FRINGE_MAX_SIZE = 100000;
  private Node root;
  private QueueLinkedList fringe;
  private int len = 0;
  private Node goal;
  private int numNodesExpanded;
  private Node[] closed;
  private int n_closed;
  
  Search2(State paramState)
  {
    this.root = new Node(paramState, null, -1, 0, 0);
    
    this.fringe = new QueueLinkedList();
    this.closed = new Node[100000];
    this.n_closed = 0;
    this.goal = null;
    this.numNodesExpanded = 0;
  }
  
  private void initialize_closed()
  {
    if (this.closed == null) {
      this.closed = new Node[100000];
    }
    this.n_closed = 0;
  }
  
  public int get_n_closed()
  {
    return this.n_closed;
  }
  
  public int get_numNodesExpanded()
  {
    return this.numNodesExpanded;
  }
  
  public int getActionCosr()
  {
    return this.len;
  }
  
  private boolean visited(Node paramNode)
  {
    for (int i = 0; i < this.n_closed; i++) {
      if (this.closed[i].hasSameState(paramNode)) {
        return true;
      }
    }
    return false;
  }
  
  private void mark_as_visited(Node paramNode)
  {
    if (this.n_closed == 100000)
    {
      for (int i = 0; i < 99999; i++) {
        this.closed[i] = this.closed[(i + 1)];
      }
      this.n_closed -= 1;
    }
    else
    {
      this.closed[(this.n_closed++)] = paramNode;
    }
  }
  
  public Node doSearch()
  {
    this.numNodesExpanded = 0;
    
    Node localNode = this.root;
    
    this.fringe.enqueue(localNode, 1);
    while (!this.fringe.isEmpty())
    {
      localNode = this.fringe.serve();
      if (localNode.isGoal()) {
        return localNode;
      }
      if (!visited(localNode))
      {
        mark_as_visited(localNode);
        Node[] arrayOfNode = localNode.expand();
        this.numNodesExpanded += 1;
        for (int i = 0; i < 5; i++) {
          if (arrayOfNode[i] != null) {
            if (!visited(arrayOfNode[i])) {
              this.fringe.enqueue(arrayOfNode[i], 1);
            }
          }
        }
      }
    }
    return null;
  }
  
  public Node doSearch2()
  {
    this.numNodesExpanded = 0;
    
    this.root.priority = (this.root.getPathCost() + this.root.h_md());
    Node localNode = this.root;
    localNode.priority = (this.root.getPathCost() + this.root.h_md());
    
    this.fringe.enqueue(localNode, localNode.priority);
    while (!this.fringe.isEmpty())
    {
      localNode = this.fringe.serve();
      if (!visited(localNode))
      {
        mark_as_visited(localNode);
        if (localNode.isGoal()) {
          return localNode;
        }
        Node[] arrayOfNode = localNode.expand();
        this.numNodesExpanded += 1;
        for (int i = 0; i < 5; i++) {
          if ((arrayOfNode[i] != null) && 
            (!visited(arrayOfNode[i]))) {
            this.fringe.enqueue(arrayOfNode[i], arrayOfNode[i].getPathCost() + arrayOfNode[i].h_md());
          }
        }
      }
    }
    return null;
  }
  
  public Node doSearch3()
  {
    this.numNodesExpanded = 0;
    
    Object localObject1 = this.root;
    Object localObject2 = localObject1;
    ((Node)localObject2).priority = this.root.h_md();
    if (((Node)localObject2).isGoal()) {
      return (Node)localObject2;
    }
    for (;;)
    {
      Node[] arrayOfNode = ((Node)localObject2).expand();
      this.numNodesExpanded += 1;
      for (int i = 0; i < 5; i++) {
        if (arrayOfNode[i] != null)
        {
          System.out.println(arrayOfNode[i].h_md() + " " + i + " " + ((Node)localObject2).h_md());
          if (arrayOfNode[i].h_md() < ((Node)localObject2).h_md())
          {
            System.out.println(arrayOfNode[i].h_md() + " " + i + " " + ((Node)localObject2).h_md());
            localObject2 = arrayOfNode[i];
          }
        }
      }
      if (((Node)localObject2).isGoal()) {
        return (Node)localObject2;
      }
      if (localObject2 == localObject1) {
        return (Node)localObject2;
      }
      localObject1 = localObject2;
    }
  }
  
  public void getSolAndwrite(int paramInt, String paramString)
  {
    String[] arrayOfString = null;
    FileWriter localFileWriter = null;
    BufferedWriter localBufferedWriter = null;
    if (paramInt == 1)
    {
      this.goal = doSearch();
      if (this.goal != null) {
        arrayOfString = extractSolution(this.goal);
      }
    }
    else if (paramInt == 2)
    {
      this.goal = doSearch2();
      if (this.goal != null) {
        arrayOfString = extractSolution(this.goal);
      }
    }
    else if (paramInt == 3)
    {
      this.goal = doSearch3();
      if (this.goal != null) {
        arrayOfString = extractSolution(this.goal);
      }
    }
    int i;
    if (this.goal.isGoal()) {
      try
      {
        File localFile1 = new File(paramString);
        
        localFile1.createNewFile();
        
        localFileWriter = new FileWriter(localFile1.getAbsoluteFile(), false);
        localBufferedWriter = new BufferedWriter(localFileWriter);
        for (i = 0; i < arrayOfString.length; i++)
        {
          localBufferedWriter.write(arrayOfString[i]);
          localBufferedWriter.newLine();
        }
        localBufferedWriter.close();
      }
      catch (Exception localException1)
      {
        System.out.println("SOMETHING WRONG WITH THE WRITE METHOD");
      }
    } else {
      try
      {
        File localFile2 = new File(paramString);
        
        localFile2.createNewFile();
        
        localFileWriter = new FileWriter(localFile2.getAbsoluteFile(), false);
        localBufferedWriter = new BufferedWriter(localFileWriter);
        System.out.println(arrayOfString.length);
        for (i = 0; i < arrayOfString.length; i++)
        {
          System.out.println(i);
          localBufferedWriter.write(arrayOfString[i]);
          localBufferedWriter.newLine();
        }
        localBufferedWriter.write("NOOP");
        localBufferedWriter.newLine();
        localBufferedWriter.close();
      }
      catch (Exception localException2)
      {
        System.out.println("SOMETHING WRONG WITH THE WRITE METHOD " + localException2.getMessage());
      }
    }
  }
  
  public String[] extractSolution(Node paramNode)
  {
    this.len = 0;
    Node localNode = paramNode;
    while (localNode != null)
    {
      localNode = localNode.parent;
      this.len += 1;
    }
    String[] arrayOfString = new String[this.len - 1];
    
    localNode = paramNode;
    for (int i = this.len - 2; i >= 0; i--)
    {
      switch (localNode.action)
      {
      case 0: 
        arrayOfString[i] = new String("move-N");
        break;
      case 1: 
        arrayOfString[i] = new String("move-S");
        break;
      case 2: 
        arrayOfString[i] = new String("move-W");
        break;
      case 3: 
        arrayOfString[i] = new String("move-E");
        break;
      case 4: 
        arrayOfString[i] = new String("recharge");
      }
      localNode = localNode.parent;
    }
    return arrayOfString;
  }
  
  public void displaySolution(Node paramNode)
  {
    String[] arrayOfString = extractSolution(paramNode);
    for (int i = 0; i < arrayOfString.length; i++) {
      System.out.println(arrayOfString[i]);
    }
  }
}
