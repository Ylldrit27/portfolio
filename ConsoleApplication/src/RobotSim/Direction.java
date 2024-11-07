package RobotSim;

import java.util.Random;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	/**
	 * Method to get a random direction. Uses values() to get an array of all enum
	 * values, and selects one randomly
	 */
	public static Direction getRandomDirection() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

	/**
	 * Method to get next direction in sequence. Uses the ordinal value of the
	 * current direction, incrementing it and using modulo to cycle through the
	 * directions
	 */
	public Direction nextDirection() {
		return values()[(this.ordinal() + 1) % values().length];
	}
}