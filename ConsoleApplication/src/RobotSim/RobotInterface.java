package RobotSim;

import java.util.Scanner;

/**
 * Simple program to show arena with multiple robots
 * 
 * @author Ylldrit Miftari
 *
 */
public class RobotInterface {

	private Scanner s; // scanner used for input from user
	private RobotArena myArena; // arena in which Robots are shown
	private TextFile tf = new TextFile("Text files", "txt"); // object used to write and read files

	/**
	 * constructor for RobotInterface sets up scanner used for input and the arena
	 * then has main loop allowing user to enter commands
	 */
	public RobotInterface() {
		s = new Scanner(System.in); // set up scanner for user input
		myArena = new RobotArena(20, 6); // create arena of size 20*6

		char ch = ' ';
		do {
			System.out.print("Enter (A)dd Robot, (D)isplay arena, get (I)nformation, "
					+ "(M)ove Robots, (N)ew arena, (S)ave arena , (L)oad arena, " + "(R)un Animation or e(X)it > ");
			ch = s.next().charAt(0);
			s.nextLine();
			switch (ch) {
			case 'A':
			case 'a':
				myArena.addRobot(); // add a new Robot to arena
				break;
			case 'D':
			case 'd':
				doDisplay(); // display the arena with robots
				break;
			case 'I':
			case 'i':
				System.out.print(myArena.toString()); // display information on arena & robots
				break;
			case 'M':
			case 'm':
				doMove(); // moves all robots at once, then redraws the arena
				break;
			case 'N':
			case 'n':
				createNewArena(); // call the method to create a new arena
				break;
			case 'S':
			case 's':
				if (tf.createFile()) // if can create file
					tf.writeAllFile(myArena.fileString()); // write arena to file
				break;
			case 'L':
			case 'l':
				if (tf.openFile()) {
					// if selected file exists, read & set up arena
					String fs = tf.readAllFile();
//                        myArena = new RobotArena(fs);
				} else {
					System.out.println("Failed to load arena");
				}
				break;
			case 'R':
			case 'r':
				doAnimate(); // runs animation on robots, moving 10 times
				break;
			case 'x':
				ch = 'X'; // when X detected program ends
				break;
			}
		} while (ch != 'X'); // test if end

		s.close(); // close scanner
	}

	/**
	 * Create a new arena based on user input
	 */
	private void createNewArena() {
		System.out.print("Enter new x size of arena: ");
		int xSize = s.nextInt();
		System.out.print("Enter new y size of arena: ");
		int ySize = s.nextInt();
		s.nextLine(); // clear the buffer
		myArena = new RobotArena(xSize, ySize); // create a new arena with specified size
		System.out.println("New arena created with size " + xSize + " by " + ySize);
	}

	/**
	 * Display the Robot arena on the console
	 */
	void doDisplay() {
		// determine the arena size using getter methods
		int xSize = myArena.getX();
		int ySize = myArena.getY();

		// create a ConsoleCanvas object with the appropriate size
		ConsoleCanvas canvas = new ConsoleCanvas(xSize, ySize, "32004724");

		// call showRobots method on the arena
		myArena.showRobots(canvas);

		// print the string representation of the ConsoleCanvas
		System.out.println(canvas.toString());
	}

	/**
	 * Move all robots simultaneously, then update + redraw arena
	 */
	void doMove() {
		myArena.moveAllRobots(); // Move all robots once
		doDisplay(); // Redraw the arena
		System.out.print(myArena.toString()); // Print information about all robots
	}

	/**
	 * Method called doAnimate which moves all the robots 10 times, redraws the
	 * arena each time, lists the robots' positions, and includes a 200ms delay
	 */
	void doAnimate() {
		for (int i = 0; i < 10; i++) {
			myArena.moveAllRobots(); // Move all robots once
			doDisplay(); // Redraw the arena
			System.out.print(myArena.toString()); // Print information about all robots

			// Delay for 200 milliseconds
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.err.println("Animation interrupted");
			}
		}
	}

	public static void main(String[] args) {
		RobotInterface r = new RobotInterface(); // just call the interface
	}

}