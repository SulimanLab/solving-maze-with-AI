// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  suliman hassan aljarbua
// ID: 435102530

import java.io.*;
import java.util.*;

public class Search {

	// CONSTANTS:
	private static final int CLOSED_MAX_SIZE = 100000;
	private static final int FRINGE_MAX_SIZE = 100000;

	// ATTRIBUTES:
	private Node root; // the root node
	private QueueLinkedList fringe; // the BFS frings: change it // for the
	private int len = 0; // other searches.
	private Node goal; // the goal node
	private int numNodesExpanded; // number of nodes expanded

	// ALTHOUGH YOU ARE NOT REQUIRED TO, BUT IT IS
	// HIGHLY RECOMMENDED THAT YOU IMPLEMENT THE
	// CLOSED LIST MECHANISM (THE GRAPH SEARCH).
	// THE NEED FOR IT IS DUE TO THE VERY LARGE NUMBER OF
	// REDUNDANT STATES THAT YOU WILL GENERATE BEFORE
	// REACHING THE GOAL.
	// THE FOLLOWING IS A SIMPLE IMPLEMENTATION OF THE CLOSED
	// LIST, REPRESENTED AS A SIMPLE ARRAY OF NODES:

	private Node closed[]; // the closed list containing // visited nodes
	private int n_closed; // size of closed list

	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A SEARCH OBJECT.
	Search(State init_state) {
		root = new Node(init_state, null, -1, 0, 0); // make the root
														// node
		fringe = new QueueLinkedList(); // initialize Queue
		closed = new Node[CLOSED_MAX_SIZE];
		n_closed = 0;
		goal = null;
		numNodesExpanded = 0;

		// ...

	}

	// THIS METHOD INITIALIZES THE CLOSED LIST
	private void initialize_closed() { // finish
		if (closed == null)
			closed = new Node[CLOSED_MAX_SIZE];
		n_closed = 0;
	}

	public int get_n_closed() {
		return n_closed;
	}

	public int get_numNodesExpanded() {
		return numNodesExpanded;
	}

	public int getActionCosr() {
		return len;
	}

	// THIS METHOD TESTS WHETHER THE NODE WAS
	// VISITED OR NOT USING A SIMPLE FOR LOOP.
	// YOU CAN CHANGE IT.
	private boolean visited(Node n) { // finish
		for (int i = 0; i < n_closed; i++) {

			if (closed[i].hasSameState(n))
				return true;
		}
		return false;
	}

	// THIS METHOD ADDS A NODE TO THE CLOSED LIST.
	// IT SIMPLY ADDS IT TO THE ND OF THE LIST. YOU
	// CAN CHANGE IT TO A MORE SOPHISTICATED METHOD.
	private void mark_as_visited(Node n) { // finish
		// if the list is full do a left shift:
		// if (n_closed == CLOSED_MAX_SIZE) {
		// System.out.println("n_closed == CLOSED_MAX_SIZE");
		// for (int i = 0; i < CLOSED_MAX_SIZE - 1; i++)
		// closed[i] = closed[i + 1];
		// } else {
		// System.out.println("help mee"+n.action);
		// closed[n_closed++] = n;
		// }
		//
		//// closed[n_closed - 1] = n;

		// if the list is full do a left shift:
		if (n_closed == CLOSED_MAX_SIZE) {
			for (int i = 0; i < CLOSED_MAX_SIZE - 1; i++)
				closed[i] = closed[i + 1];
			n_closed--;
		} else
			closed[n_closed++] = n;
	}

	// THIS METHOD WILL DO THE SEARCH AND CAN
	// RETURN THE GOAL NODE. YOU CAN EXTRACT
	// THE SOLUTION BY FOLLOWING THE PARENT NODES
	public Node doSearch() { // BFS finished

		numNodesExpanded = 0;
		Node nodesList[];
		Node current = root;
		// current.priority = 1;
		fringe.enqueue(current, 1);

		while (!fringe.isEmpty()) {

			current = fringe.serve();

			if (current.isGoal()) {
				return current;
			}
			if (!visited(current)) {
				mark_as_visited(current);
				nodesList = current.expand();
				numNodesExpanded++;
				// System.out.println();
				for (int i = 0; i < 5; i++) { // we have 5 actions
					// ...
					if (nodesList[i] != null) {

						if (!visited(nodesList[i]))
							fringe.enqueue(nodesList[i], 1);
						// System.out.println("do search " + i);

					}

				}
			}
		}
		return null; // goal not found

	}

	public Node doSearch2() { // A* finished

		numNodesExpanded = 0;
		Node nodesList[];
		root.priority = root.getPathCost() + root.h_md();
		Node current = root;
		current.priority = root.getPathCost() + root.h_md();

		fringe.enqueue(current, current.priority);
		while (!fringe.isEmpty()) {
			current = fringe.serve();
			// System.out.println(fringe.length());
			// System.out.println(current.action + ">>>>>>>>>>>");
			if (!visited(current)) {
				mark_as_visited(current);
				if (current.isGoal()) {
					return current;
				}

				nodesList = current.expand();
				numNodesExpanded++;
				for (int i = 0; i < 5; i++) { // we have 5 actions
					// ...
					if (nodesList[i] != null) {
						if (!visited(nodesList[i])) {

							//
							// if (nodesList[i].h_md() < 0)
							// getH_md = nodesList[i].h_md() - (2 *
							// nodesList[i].h_md());
							// System.out.println(nodesList[i].getPathCost() +
							// "-" + i);
							fringe.enqueue(nodesList[i], nodesList[i].getPathCost() + nodesList[i].h_md());
						}
					}

				}
			}
		}
		return null; // goal not found

	}

	public Node doSearch3() {// Hill-climbing

		numNodesExpanded = 0;
		Node nodesList[];

		Node current = root;
		Node highest = current;
		highest.priority = root.h_md();
		// if (highest.priority < 0)
		// highest.priority = highest.h_md() - (2 * highest.h_md());

		if (highest.isGoal()) {
			return highest;
		}

		while (true) {

			nodesList = highest.expand();
			numNodesExpanded++;
			for (int i = 0; i < 5; i++) { // we have 5 actions
				// ...
				if (nodesList[i] != null) {
					// int getH_md = highest.h_md();
					// if (highest.h_md() < 0)
					// getH_md = highest.h_md() - (2 * highest.h_md());
					System.out.println(nodesList[i].h_md() + " " + i + " " + highest.h_md());
					if (nodesList[i].h_md() < highest.h_md()) {
						System.out.println(nodesList[i].h_md() + " " + i + " " + highest.h_md());
						highest = nodesList[i];
					}
				}

			}
			if (highest.isGoal())
				return highest;
			if (highest == current)// didn't change
				return highest;

			current = highest;
		}

	}

	public void getSolAndwrite(int na, String actionFile) {
		String sol[] = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		if (na == 1) {
			goal = doSearch();
			if (goal != null) {
				sol = extractSolution(goal);
			}

		} else if (na == 2) {
			goal = doSearch2();
			if (goal != null) {

				sol = extractSolution(goal);
			}

		} else if (na == 3) {
			goal = doSearch3();
			if (goal != null) {

				sol = extractSolution(goal);
			}
		}

		if (goal.isGoal()) {
			try {
				File file = new File(actionFile);

				// if (!file.exists())
				file.createNewFile();

				fw = new FileWriter(file.getAbsoluteFile(), false);
				bw = new BufferedWriter(fw);
				for (int i = 0; i < sol.length; i++) {
					bw.write(sol[i]);
					bw.newLine();
				}
				bw.close();
			} catch (Exception e) {
				System.out.println("SOMETHING WRONG WITH THE WRITE METHOD");

			}

		} else {

			try {
				File file = new File(actionFile);

				// if (!file.exists())
				file.createNewFile();

				fw = new FileWriter(file.getAbsoluteFile(), false);
				bw = new BufferedWriter(fw);
				System.out.println(sol.length);
				for (int i = 0; i < sol.length; i++) {
					System.out.println(i);
					bw.write(sol[i]);
					bw.newLine();
				}
				bw.write("NOOP");
				bw.newLine();
				bw.close();

			} catch (Exception e) {

				System.out.println("SOMETHING WRONG WITH THE WRITE METHOD " + e.getMessage());

			}
		}
	}

	// GIVEN THE GOAL NODE, THIS METHOD WILL EXTRACT
	// THE SOLUTION, WHICH IS A SEQUENCE OF ACTIONS.
	public String[] extractSolution(Node goalNode) {

		// first find solution length;
		len = 0;
		Node n = goalNode;
		while (n != null) {
			n = n.parent;
			len++;
		}
		// declare an array of strings
		String sol[] = new String[len - 1];
		n = goalNode;
		for (int i = len - 2; i >= 0; i--) {
			switch (n.action) {
			case 0:
				sol[i] = new String("move-N");
				break;
			case 1:
				sol[i] = new String("move-S");
				break;
			case 2:
				sol[i] = new String("move-W");
				break;
			case 3:
				sol[i] = new String("move-E");
				break;
			case 4:
				sol[i] = new String("recharge");
			}
			n = n.parent;
		}
		return sol;
	}

	// THIS METHOD WILL DISPLAY THE SOLUTION
	public void displaySolution(Node goalNode) {
		String sol[] = extractSolution(goalNode);
		for (int i = 0; i < sol.length; i++)
			System.out.println(sol[i]);
	}

}
