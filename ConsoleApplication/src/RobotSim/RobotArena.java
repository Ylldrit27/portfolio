package RobotSim;

import java.util.ArrayList; // import ArrayList class
import java.util.Random; // import Random class

public class RobotArena {

	/**
	 * Class which creates the robot arena for many robots to spawn in randomly
	 */

	private int xmax, ymax; // defines size of arena
	private Robot r; // define a single robot
	private int rx, ry; // defines coordinates of first robot

	ArrayList<Robot> manyRobots; // declare an ArrayList called manyRobots

	/**
	 * Constructor to create the arena
	 */
	public RobotArena(int x, int y) {

		// create an *empty* ArrayList from manyRobot variable
		manyRobots = new ArrayList<Robot>();

		xmax = x; // create x coordinate for arena
		ymax = y; // create y coordinate for arena
	}

	/**
	 * A method which spawns a robot in a random location of the arena
	 */
	public void addRobot() {

		Random randomGenerator; // declare a Random object
		randomGenerator = new Random(); // creating a Random object

		do {
			rx = randomGenerator.nextInt(xmax); // Create x-coordinate for robot within arena
			ry = randomGenerator.nextInt(ymax); // Create y-coordinate for robot within arena
		} while (getRobotAt(rx, ry) != null); // Ensure no robot exists at the chosen position

		Direction randomDirection = Direction.getRandomDirection();

		manyRobots.add(r = new Robot(rx, ry, randomDirection)); // Creates a robot and adds to manyRobots ArrayList
	}

	/**
	 * Search ArrayList (manyRobots) to check if there is a robot at x,y
	 * 
	 * @param x Check x-coordinate
	 * @param y Check y-coordinate
	 * @return null if no Robot is found, otherwise return Robot
	 */
	public Robot getRobotAt(int x, int y) {
		for (Robot r : manyRobots) {
			if (r.isHere(x, y)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Show all the Robots in the interface
	 * 
	 * @param c the canvas in which Robots are shown
	 */
	public void showRobots(ConsoleCanvas c) {
		for (Robot r : manyRobots) {
			r.displayRobot(c); // call the displayRobot method for each robot
		}
	}

	/**
	 * Get x size of the arena
	 * 
	 * @return x
	 */
	public int getX() {
		return xmax;
	}

	/**
	 * Get y size of the arena
	 * 
	 * @return y
	 */
	public int getY() {
		return ymax;
	}

	/**
	 * Method to check the robot's position and boundaries and also for any existing
	 * robots
	 * 
	 * @param x x-coord of robot
	 * @param y y-coord of robot
	 * @return calls getRobotAt() to check if there's already a robot at that
	 *         specific position
	 */
	public boolean canMoveHere(int x, int y) {
		// Check if the new coordinates are within the arena
		if (x < 0 || x >= xmax || y < 0 || y >= ymax) {
			return false; // Position is outside the arena
		}

		// Check if there is already a robot at the new coordinates
		return getRobotAt(x, y) == null;
	}

	/**
	 * Method to move many robots simultaneously
	 */
	public void moveAllRobots() {
		for (Robot r : manyRobots) {
			r.tryToMove(this);
		}
	}

	/**
	 * return file information about arena and all robot contents
	 */
	public String fileString() {
		String ans = "";

		// Enhanced for loop method which concatenates many robots' coordinates to
		// ArrayList
		for (Robot r : manyRobots)
			ans += r.fileString() + "\n";

		return xmax + " " + ymax + "\n" + ans;
	}

	/**
	 * return information about arena and all robot contents
	 */
	public String toString() {
		String ans = "";

		// Enhanced for loop method which concatenates many robots' coordinates to
		// ArrayList
		for (Robot r : manyRobots)
			ans += r.toString() + "\n";

		return "Arena " + xmax + " by " + ymax + " with: " + "\n" + ans;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Code to test arena
		RobotArena a = new RobotArena(20, 10); // create Robot arena
		a.addRobot(); // add first robot to arena
		a.addRobot(); // add second robot to arena
		a.addRobot(); // add third robot to arena
//		System.out.println(a.toString());	// print arena size and where robots are
		System.out.println(a.fileString()); // print arena size and where robots are
	}

}
