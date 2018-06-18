import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  suliman hassan aljarbua
// ID: 435102530

public class Agent {

	public static void main(String[] args) throws Exception {

		int n_args = args.length;
		if (n_args != 5) {
			System.out.println("ERROR: ILLEGAL NUMBER OF ARGUMENTS.");
			System.out.println("Number of arguments must be 5");
			return;
		}
		
		String mode = args[0];
		
		if (mode.equals("c")) {
			System.out.println("im here 2");
			String mapFile = args[1];
			String commandsFile = args[2];
			String finalMapFile = args[3];
			String logFile = args[4];

			// WRITE YOUR CODE DOWN HERE:

			// ....
			State s1 = new State(mapFile);
			s1.display2();

			BufferedReader br = new BufferedReader(new FileReader(commandsFile));
			String cmd = br.readLine();
			File file = new File(logFile);
			if (file.exists())
				file.delete();
			while (cmd != null) {
				System.out.println(cmd);
				s1.writeLogs(logFile, s1.doCommandAndLog(cmd));
				cmd = br.readLine();
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(finalMapFile));

			for (int j = 0; j < s1.getM(); j++) {

				for (int i = 0; i < s1.getN(); i++) {

					bw.write(s1.display()[i][j]);
				}

				bw.newLine();
			}
			bw.close();

		} else if (mode.equals("s")) {
			int na = Integer.parseInt(args[1]);
			String mapFile = args[2];
			String actionFile = args[3];
			String finalMapFile = args[4];
			
			State s2 = new State(mapFile);
			Search search = new Search(s2);
			long start = System.currentTimeMillis();
			search.getSolAndwrite(na, actionFile);
			s2.doCommandAndLog(actionFile);
			System.out.println(search.get_numNodesExpanded()+" <<<<<<< im the explred nodes");
			System.out.println(search.getActionCosr()+"<<<<<<<< im the action cost");
			long end = System.currentTimeMillis();
			long time = end - start;
			System.out.println("im the time"+ time);
			BufferedWriter bw = new BufferedWriter(new FileWriter(finalMapFile));
			for (int j = 0; j < s2.getM(); j++) {

				for (int i = 0; i < s2.getN(); i++) {

					bw.write(s2.display()[i][j]);
				}

				bw.newLine();
			}
			bw.close();

			if (na == 1) { // BFS

			} else if (na == 2) { // A*

			} else if (na == 3) { // H_C

			}

		}

	}

}
