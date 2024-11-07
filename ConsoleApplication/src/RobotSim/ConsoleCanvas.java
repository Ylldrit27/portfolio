package RobotSim;

public class ConsoleCanvas {

	private int xSize, ySize;
	private char[][] displayChars; // 2D array of characters

	ConsoleCanvas(int xS, int yS, String userStr) {
		xSize = xS + 2;
		ySize = yS + 2;
		displayChars = new char[xSize][ySize]; // create array big enough for canvas and border
		padChars(' ', '#', userStr); // now set up array, spaces, and border
	}

	/**
	 * function to fill the displayChars array with character pchar, putting bchar
	 * in the border
	 * 
	 * @param pchar   character to pad array
	 * @param bchar   character used for border
	 * @param userStr 8 char string identifying user (put in centre of top row)
	 */
	private void padChars(char pchar, char bchar, String userStr) {
		int topchk = Math.max((xSize - 8) / 2, 0); // used to ensure stdnum in middle of top row : max ensures not -ve

		for (int yct = 0; yct < ySize; yct++) {
			for (int xct = 0; xct < xSize; xct++) {
				if (yct == 0 && xct >= topchk && xct < topchk + 8) { // Centre the student number on the top row
					displayChars[xct][yct] = userStr.charAt(xct - topchk);
				} else if (xct == 0 || xct == xSize - 1 || yct == 0 || yct == ySize - 1) {
					displayChars[xct][yct] = bchar; // Create border for the entire boundary
				} else {
					displayChars[xct][yct] = pchar; // Fills inside arena with pchar
				}
			}
		}
	}

	/**
	 * specific what is shown at position x,y
	 * 
	 * @param x
	 * @param y
	 * @param ch
	 */
	public void showIt(int x, int y, char ch) {
		displayChars[x + 1][y + 1] = ch; // puts ch into x, y'th element in displayChars
	}

	/**
	 * function to output a row of the building with walls/objects in it if
	 * necessary does so by outputting displayChars array, preceded and followed by
	 * pchar character
	 * 
	 * @param pchar
	 */
	public String toString() {
		String ans = "";
		for (int yct = 0; yct < ySize; yct++) {
			for (int xct = 0; xct < xSize; xct++)
				ans += displayChars[xct][yct];
			ans += '\n';
		}
		return ans;
	}

	public static void main(String[] args) {
		ConsoleCanvas c = new ConsoleCanvas(20, 6, "32004724"); // create a canvas
		c.showIt(4, 3, 'R'); // add a Robot at 4,3
		System.out.println(c.toString()); // display result
	}

}
