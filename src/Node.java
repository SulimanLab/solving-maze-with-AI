// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  suliman hassan aljarbua
// ID: 435102530

import java.io.*;
import java.util.*;

class Node {
	public State state; // the state
	Node parent; // the parent node
	int action; // the number of the action
				// that lead to this state
	private int path_cost; // the cost spent so far to reach this node
	public int depth; // the depth of the node in the tree
	public int priority;
	public Node next;

	// CONSTRUCTOR :
	// THIS CONSTRUCTOR WILL CREATE A NODE GIVEN A STATE
	Node(State st, Node pa, int a, int c, int d) {
		state = st;
		parent = pa;
		action = a;
		path_cost = c;
		depth = d;
	}

	Node(Node e, int pri) {
		this(e.state, e.parent, e.action, e.path_cost, e.depth);
//		state = e.state;
//		parent = e.parent;
//		action = e.action;
//		path_cost = e.path_cost;
//		depth = e.depth;
		priority = pri;
	}

	public int getPathCost() {
		return path_cost;
	}

	// THIS METHOD RETURNS TRUE IS THE
	// NODE'S STATE IS THE SAME AS THE OTHER NODE'S STATE
	public boolean hasSameState(Node n) {
		return (state.equal(n.state));
	}

	// THIS METHOD WILL RETURN THE NEIGHBORING NODES
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
	public Node[] expand() {
		int c = 0;
		Node next_nodes[] = new Node[5]; // there are 5 actions
		State next_states[] = state.successors();
		for (int i = 0; i < 5; i++) { // create nodes
			if (next_states[i] != null) {
				System.out.println("expand "+ i);
				next_nodes[c++] = new Node(next_states[i], this, i, (path_cost + 1), depth + 1);
			}
		}
		return next_nodes;
	}

	// GOAL TEST: THIS WILL TELL
	// WHETHER THE NODE'S STATE IS A GOAL.
	public boolean isGoal() {
		return state.foundTreasure();
	}

	// MANHATTAN DISTANCE HEURISTIC
	public int h_md() {
//		return 100 - ((state.rob.x - state.getTx()) + (state.rob.y - state.getTy()));
		return Math.abs(state.getRx() - state.getTx()) + Math.abs(state.getRy() - state.getTy());
//		return state.getTx() - state.getRx() + state.getTy() - state.getRy();
	}

	// DISPLAY THE NODE'S INFO
	public void display() {

		// ...

	}
}
