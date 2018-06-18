// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  suliman hassan aljarbua
// ID: 435102530
public class QueueLinkedList {
	private Node head;
	private int size;

	public QueueLinkedList() {
		head = null;
		size = 0;
	}

	public int length() {
		return size;
	}

	public boolean full() {
		return false;
	}

	// public void enqueue(Node e, int pri) {
	// Node t = new Node(e, pri);
	// if (size == 0 || pri > head.priority) {
	// t.next = head;
	// head = t;
	// size++;
	// }
	//
	// else {
	// Node p = head;
	// Node q = null;
	//
	// while ((p != null && p.priority >= pri)) {
	// q = p;
	// p = p.next;
	// }
	// t.next = p;
	// q.next = t;
	//
	// size++;
	//
	// }
	// }

	public void enqueue(Node e, int pty) {
		Node tmp = new Node(e, pty);
		
		if (size == 0 || pty < head.priority) {
			tmp.next = head;
			head = tmp;
		} else {
			Node p = head;
			Node q = null;
			while ((p != null) && (pty >= p.priority)) {
//				System.out.println("im here");
				q = p;
				p = p.next;
			}
			q.next = tmp;
			tmp.next = p;
//			if(tmp == p)
//				head = tmp;
		}

		size++;
	}
	// public Node serve() {
	// System.out.println("a;dlfjals;kdjfal;ksjdf;laksjd;flkajs;lkdfja;sldkjfa;lskdjfa;lsdj>>>>>im
	// serving ");
	// Node node = head;
	// Node p = new Node(node, node.priority);
	// head = head.next;
	// size--;
	// return p;
	// }

	public Node serve() {
		Node n = head;
		Node x = new Node(n, n.priority);
		head = head.next;
		size--;
		return x;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
}
