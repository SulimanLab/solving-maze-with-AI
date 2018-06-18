

public class PQNode {
	public Node data;
	public int priority;
	public PQNode next;
	
	public PQNode() {
		next = null;
	}
	
	public PQNode(Node e, int p) {
		data = e;
		priority = p;
	}

	
}
