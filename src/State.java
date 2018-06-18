// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  suliman hassan aljarbua
// ID: 435102530
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class State {

	BufferedWriter bw = null;
	private int n, m, Tx, Ty;
	private char maze[][];
	char finalMapFile[][];
	private int rx, ry, battery;
	// robot rob;
	// private char r;

	// -----------------------------

	// THE FOLLOWING ARE THE CONSTRUCTORS
	// YOU CAN CHANGE OR REPALCE THEM.

	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A STATE FROM FILE.
	State(String fileName) throws Exception {
		int from = 0, until = 1;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {

			String line = br.readLine();

			n = Integer.parseInt(line); // get rows
			// System.out.println(n);
			line = br.readLine();

			m = Integer.parseInt(line);

			maze = new char[n][m]; // create the maze

			int i = 0, j = 0;
			line = br.readLine();
			int length = line.length();
			char test;
			while (line != null) {

				// System.out.println(line + "");

				while (i <= length - 1) {
					if (i == 25 && j < 24) {
						j++;
						line = br.readLine();
						if (line != null)
							length = line.length();
						i = 0;
					}
					test = line.charAt(i);

					if (test == 'R' || test == 'U' || test == 'X' || test == 'F' || test == 'D' || test == 'Z') { // create
																													// a
																													// robot
						rx = i;
						ry = j;
						battery = n + m;
						// rob = new robot(i, j, n + m);
					}
					if (test == 'T' || test == 'F' || test == 'E' || test == 'Z' || test == 'Y' || test == 'U') {
						Tx = i;
						Ty = j;
					}

					maze[i][j] = test;

					i++;
				}
				j++;
				line = br.readLine();
				if (line != null)
					length = line.length();
				i = 0;
			}

		} finally {
			br.close();
		}

		// ...
	}

	// CONSTRUCTOR 3:
	// COPY CONSTRUCTOR.
	State(State s) {

		this.rx = s.rx;
		this.ry = s.ry;
		this.battery = s.battery;
		n = s.n;
		m = s.m;

		maze = new char[n][m];
		Tx = s.Tx;
		Ty = s.Ty;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				this.maze[i][j] = s.maze[i][j];
			}

	}

	public BufferedWriter getBw() {
		return bw;
	}

	public void setBw(BufferedWriter bw) {
		this.bw = bw;
	}

	public int getRx() {
		return rx;
	}

	public void setRx(int rx) {
		this.rx = rx;
	}

	public int getRy() {
		return ry;
	}

	public void setRy(int ry) {
		this.ry = ry;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public void setTx(int tx) {
		Tx = tx;
	}

	public void setTy(int ty) {
		Ty = ty;
	}

	// -----------------------------
	public int getTx() {
		return Tx;
	}

	public int getTy() {
		return Ty;
	}

	// public int getX() {
	//
	// return rx;
	// }
	//
	// public int getY() {
	// return ry;
	// }

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public char[][] getMaze() {
		return maze;
	}

	public void setMaze(char[][] maze) {
		this.maze = maze;
	}

	// public robot getRob() {
	// return rob;
	// }
	//
	// public void setRob(robot rob) {
	// this.rob = rob;
	// }

	// METHOD THAT TELLS WHETHER THIS STATE IS EQUAL
	// TO ANOTHER STATE.
	public boolean equal(State s) {

		return ((n == s.n) && (m == s.m) && (battery == s.battery) && (rx == s.rx) && (ry == s.ry));
	}

	// -----------------------------

	// THE ACTIONS:
	// YOU CAN CHANGE THE ACTIONS CONTENTS,
	// WHAT THE ACTIONS RETURN, ETC.

	// ACTION 1
	// RETURNS BOOLEAN:
	// TRUE MEANS ACTION WAS APPLIED,
	// FALSE MEANS ACTOIN COULD NOT AND WAS NOT APPLIED.
	public boolean moveN() { // move up
		if (battery == 0)
			return false;
		char currentPos = maze[rx][ry];
		if (ry > 0) { // checks whether i can go up

			if (maze[rx][ry - 1] != 'B' && currentPos != 'X' && currentPos != 'Z') {

				if (currentPos != 'U' && currentPos != 'D' && currentPos != 'F') {
					battery--;
					switch (maze[rx][ry - 1]) {
					case ' ':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx][ry--] = ' ';
						maze[rx][ry] = 'F';
						break;
					}

					return true;
				}

				else if (currentPos == 'U') {
					battery--;
					switch (maze[rx][ry - 1]) {
					case ' ':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx][ry--] = 'T';
						maze[rx][ry] = 'F';
						break;
					}
					return true;
				} else if (currentPos == 'D') {

					switch (maze[rx][ry - 1]) {
					case ' ':
						// System.out.println("giddy up ");
						maze[rx][ry--] = 'C';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry--] = 'C';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry--] = 'C';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry--] = 'C';
						maze[rx][ry] = 'Z';
						break;
					}
					battery--;
					return true;
				} else if (currentPos == 'F') {
					battery--;
					switch (maze[rx][ry - 1]) {
					case ' ':
						maze[rx][ry--] = 'E';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry--] = 'E';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry--] = 'E';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry--] = 'E';
						maze[rx][ry] = 'Z';
						break;
					}
					return true;
				}
				return false;
			}

		}
		return false;
	}

	public boolean moveS() { // move down
		if (battery == 0)
			return false;
		char currentPos = maze[rx][ry];
		if (ry < getM() - 1) { // checks whether i can go up

			if (maze[rx][ry + 1] != 'B' && currentPos != 'X' && currentPos != 'Z') {

				if (currentPos != 'U' && currentPos != 'D' && currentPos != 'F') {
					battery--;
					switch (maze[rx][ry + 1]) {
					case ' ':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx][ry++] = ' ';
						maze[rx][ry] = 'F';
						break;
					}

					return true;
				}

				else if (currentPos == 'U') {
					battery--;
					switch (maze[rx][ry + 1]) {
					case ' ':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx][ry++] = 'T';
						maze[rx][ry] = 'F';
						break;
					}
					return true;
				} else if (currentPos == 'D') {

					switch (maze[rx][ry + 1]) {
					case ' ':
						// System.out.println("giddy up ");
						maze[rx][ry++] = 'C';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry++] = 'C';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry++] = 'C';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry++] = 'C';
						maze[rx][ry] = 'Z';
						break;
					}
					battery--;
					return true;
				} else if (currentPos == 'F') {
					battery--;
					switch (maze[rx][ry + 1]) {
					case ' ':
						maze[rx][ry++] = 'E';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx][ry++] = 'E';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx][ry++] = 'E';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx][ry++] = 'E';
						maze[rx][ry] = 'Z';
						break;
					}
					return true;
				}
				return false;
			}

		}
		return false;
	}

	public boolean moveE() { // move right
		if (battery == 0)
			return false;
		char currentPos = maze[rx][ry];
		if (rx < getN() - 1) { // checks whether i can go up

			if (maze[rx + 1][ry] != 'B' && currentPos != 'X' && currentPos != 'Z') {

				if (currentPos != 'U' && currentPos != 'D' && currentPos != 'F') {

					switch (maze[rx + 1][ry]) {
					case ' ':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx++][ry] = ' ';
						maze[rx][ry] = 'F';
						break;
					}
					battery--;
					return true;
				}

				else if (currentPos == 'U') {

					switch (maze[rx + 1][ry]) {
					case ' ':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx++][ry] = 'T';
						maze[rx][ry] = 'F';
						break;
					}
					battery--;
					return true;
				} else if (currentPos == 'D') {

					switch (maze[rx + 1][ry]) {
					case ' ':
						maze[rx++][ry] = 'C';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx++][ry] = 'C';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx++][ry] = 'C';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx++][ry] = 'C';
						maze[rx][ry] = 'Z';
						break;
					}
					battery--;
					return true;
				} else if (currentPos == 'F') {

					switch (maze[rx + 1][ry]) {
					case ' ':
						maze[rx++][ry] = 'E';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx++][ry] = 'E';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx++][ry] = 'E';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx++][ry] = 'E';
						maze[rx][ry] = 'Z';
						break;
					}
					battery--;
					return true;
				}

				return false;
			}
		}
		return false;
	}

	public boolean moveW() { // move right
		if (battery == 0)
			return false;
		char currentPos = maze[rx][ry];
		if (rx > 0) { // checks whether i can go up

			if (maze[rx - 1][ry] != 'B' && currentPos != 'X' && currentPos != 'Z') {
				if (currentPos != 'U' && currentPos != 'D' && currentPos != 'F') {
					battery--;
					switch (maze[rx - 1][ry]) {
					case ' ':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx--][ry] = ' ';
						maze[rx][ry] = 'F';
						break;
					}

					return true;
				}

				else if (currentPos == 'U') {
					battery--;
					switch (maze[rx - 1][ry]) {
					case ' ':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'Z';
						break;
					case 'C':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'D';
						break;
					case 'E':
						maze[rx--][ry] = 'T';
						maze[rx][ry] = 'F';
						break;
					}
					return true;
				} else if (currentPos == 'D') {
					// System.out.println("lsjdfl;ksaj");
					battery--;
					switch (maze[rx - 1][ry]) {
					case ' ':
						maze[rx--][ry] = 'C';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx--][ry] = 'C';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx--][ry] = 'C';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx--][ry] = 'C';
						maze[rx][ry] = 'Z';
						break;
					}
					return true;
				} else if (currentPos == 'F') {
					battery--;
					switch (maze[rx - 1][ry]) {
					case ' ':
						maze[rx--][ry] = 'E';
						maze[rx][ry] = 'R';
						break;
					case 'H':
						maze[rx--][ry] = 'E';
						maze[rx][ry] = 'X';
						break;
					case 'T':
						maze[rx--][ry] = 'E';
						maze[rx][ry] = 'U';
						break;
					case 'Y':
						maze[rx--][ry] = 'E';
						maze[rx][ry] = 'Z';
						break;
					}
					return true;
				}
				return false;
			}
		} else
			return false;
		return false;
	}

	public boolean charge() {

		if (maze[rx][ry] == 'D' || maze[rx][ry] == 'F') {
			// if (battery == 11)
			// return true;
			// System.out.println("asdfasdf");
			this.battery = n + m;
			// System.out.println(battery);
			return true;
		}
		return false;
	}
	// -----------------------------

	// GOAL TESt: THIS WILL
	// TELL WHETHER THE TREASURE WAS FOUND.
	public boolean foundTreasure() {
		if (maze[rx][ry] == 'Z' || maze[rx][ry] == 'U' || maze[rx][ry] == 'F') {
			return true;
		}
		return false;
	}

	// -----------------------------

	// DISPLAY THE STATE
	public void display2() {

		for (int j = 0; j < getM(); j++) {
			for (int i = 0; i < getN(); i++) {
				System.out.print(maze[i][j]);
			}
			System.out.println("\n");
		}
		System.out.println("END OF DISPLAY 2");
	}

	public char[][] display() {

		return maze;
	}

	public char displayChar(int i, int j) {
		return maze[i][j];
	}

	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public String doCommandAndLog(String cmd) throws FileNotFoundException {
		String log = "ERROR";
		BufferedReader br = new BufferedReader(new FileReader(cmd));
		try {

			String line = br.readLine();
			while (line != null) {
				// int prevPosX = rx;
				// int prevPosy = ry;

				
				log = "ERROR";
				boolean check;
				switch (line) {

				case "move-N":
					check = moveN();
					if (check && foundTreasure()) {
						log = "GOAL";
					} else if (check && maze[rx][ry] == 'X') {
						log = "HOLE";
					} else if (check) {
						log = "DONE";
					} else
						log = "FAIL";
					break;

				case "move-E":
					check = moveE();
					if (check && foundTreasure()) {
						log = "GOAL";
					} else if (check && maze[rx][ry] == 'X') {
						log = "HOLE";
					} else if (check) {
						log = "DONE";
					} else
						log = "FAIL";
					break;

				case "move-W":
					check = moveW();
					if (check && foundTreasure()) {
						log = "GOAL";
					} else if (check && maze[rx][ry] == 'X') {
						log = "HOLE";
					} else if (check) {
						log = "DONE";
					} else
						log = "FAIL";
					break;
				case "move-S":
					check = moveS();
					if (check && foundTreasure()) {
						log = "GOAL";
					} else if (check && maze[rx][ry] == 'X') {
						log = "HOLE";
					} else if (check) {
						log = "DONE";
					} else
						log = "FAIL";
					break;
				case "recharge":
					check = charge();
					if (check) {
						log = "DONE";
					} else
						log = "FAIL";
					break;
				default:
					break;

				}
				line = br.readLine();

			}
			br.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return log;
	}

	// THIS METHOD WILL WRITE THE GIVEN LOGS INTO A FILE
	public void writeLogs(String logsFilename, String logs) throws Exception {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(logsFilename);

			if (!file.exists())
				file.createNewFile();

			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(logs);
			bw.write('\n');

		} catch (Exception e) {
			System.out.println("SOMETHING WRONG WITH THE WRITE METHOD");
		}
		bw.close();
	}

	// -----------------------------
	// THIS METHOD WILL RETURN THE SUCCESSOR STATES
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
	public State[] successors() {
		State children[] = new State[5]; // we have 2 actions

		// action 1

		children[0] = new State(this);
		if (!children[0].moveN())
			children[0] = null;

		// action 2

		children[1] = new State(this);
		if (!children[1].moveS())
			children[1] = null;

		children[2] = new State(this);
		if (!children[2].moveW())
			children[2] = null;

		children[3] = new State(this);
		if (!children[3].moveE())
			children[3] = null;

		children[4] = new State(this);
		if (!children[4].charge())
			children[4] = null;

		return children;
	}

	// -----------------------------

	// ADD EXTRAS HERE ...

	// ADD EXTRAS HERE ...

}

// class robot {
// char robotName = 'R';
// int x;
// int y;
// int battery;
// public robot() {
// // TODO Auto-generated constructor stub
// }
//
// robot(int xx, int yy, int battery) {
// x = xx;
// y = yy;
// this.battery = battery;
// }
//
// }
