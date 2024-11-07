package RobotSim;

public class Robot {

	private int x, y, robotId; // robot position, id and how moves in x, y direction
	private static int robotCount = 0; // used to give each robot a unique number
	private Direction direction; // specify direction of the Robot

	/**
	 * construct robot at position rx, ry
	 * 
	 * @param rx  x-position of robot
	 * @param ry  y-position of robot
	 * @param dir direction of robot
	 */
	public Robot(int rx, int ry, Direction dir) {
		x = rx;
		y = ry;
		dir = direction;
		direction = Direction.getRandomDirection(); // Initialize with a random direction
		robotId = robotCount++; // increment by 1 when a new robot is added
	}

	/**
	 * Is the robot at this x,y position
	 * 
	 * @param sx x position
	 * @param sy y position
	 * @return true if robot is at sx,sy, false otherwise
	 */
	public boolean isHere(int sx, int sy) {
		return (x == sx && y == sy);
	}

	/**
	 * display the Robot in the canvas
	 * 
	 * @param c the canvas used
	 */
	public void displayRobot(ConsoleCanvas c) {
		c.showIt(this.x, this.y, 'R');
	}

	/**
	 * Getter for the Robot's current direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Setter to change the Robot's direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Method which calculates the robots' coordinates based on current direction
	 * Uses the enum class Direction to do so
	 * 
	 * @param a an arena object as its argument
	 */
	public void tryToMove(RobotArena a) {
		// Calculate the new coordinates based on the current direction
		int newX = x, newY = y;
		switch (direction) {
		case NORTH:
			newY--; // Move up
			break;
		case EAST:
			newX++; // Move right
			break;
		case SOUTH:
			newY++; // Move down
			break;
		case WEST:
			newX--; // Move left
			break;
		}

		// Check if the new position is valid
		if (a.canMoveHere(newX, newY)) {
			x = newX;
			y = newY;
		} else {
			// Change the direction if the move is not possible
			direction = direction.nextDirection();
		}
	}

	/**
	 * return file info about robot in string
	 */
	public String fileString() {
		return robotId + " " + x + " " + y + " " + direction.toString();
	}

	/**
	 * return info about robot in string
	 */
	public String toString() {
		return "Robot " + robotId + " at (" + x + ", " + y + ")" + " facing " + direction.toString();
	}

	public static void main(String[] args) {
		Robot d = new Robot(5, 3, Direction.SOUTH); // create Robot
		System.out.println(d.toString()); // print where is
//		System.out.println(d.fileString());  // print where is (from file)
	}

}
