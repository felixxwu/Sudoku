
//import java.util.Scanner;

public class SudokuCreator {

	public static void main(String[] args) {
		int lowerBound = Integer.parseInt(args[0]);	// no lower than 18
		int upperBound = Integer.parseInt(args[1]);
		create(lowerBound, upperBound);
	}

	public static void create(int lowerBound, int upperBound) {
		Grid g = new Grid(lowerBound, upperBound); // lower and uppder bound for # of available numbers on the grid at the start
		g.initRandom(true);
		System.out.print("Looking for solutions");
		double attempts = 0;

		int[] n = new int[81];
		for (int i = 0; i < 81; i++) {
			n[i] = 1;
		}
		// System.out.println(g.getZeros());
		boolean finished = false;
		int validSolutions = 0;
		while (!finished) {
			int[][] slottedGrid = g.slotIntoGrid(n);
			if (slottedGrid[0][0] == 100) {
				int pos = slottedGrid[0][1];
				n[pos]++;
				// n = g.checkOver9(n);
				// if (n[pos] >= 10) {
				// n[pos] = 1;
				// n[pos - 1]++;
				// }
				// for (int j = 0; j < 81; j++) {
				// System.out.print(n[80 - j]);
				// }
				// System.out.println("");
			} else if (slottedGrid[0][0] == 200) {
				n = g.otherCheckOver9(n);
				if (n == null) {
					System.out.println("");
					System.out.println("All possibilities tried (solutions: " + validSolutions + ")");
					if (validSolutions == 1) {
						// g.show(slottedGrid);
						System.out.println("----- DONE! -----");
						System.out.println("Numbers: " + (81 - g.getZeros()));
						finished = true;
					} else {
						create(lowerBound, upperBound);
						return;
					}
				}
				attempts++;
				if (attempts % 10000 == 0) {
					// int[] display = new int[g.getZeros()];
					// for (int j = 0; j < g.getZeros(); j++) {
					// display[j] = n[80 - j];
					// }
					// int k = g.getZeros() - 1;
					// while (display[k] == 1 && k > 0) {
					// display[k] = 0;
					// k--;
					// }
					// for (int j = 0; j < g.getZeros(); j++) {
					// if (display[j] == 0) {
					// System.out.print("-");
					// } else {
					// System.out.print(display[j]);
					// }
					// }

					System.out.print(".");
				}
				if (attempts > 100000) {
					System.out.println("");
					System.out.println("Timeout... too many attempts");
					create(lowerBound, upperBound);
					return;
				}
			} else {
				// System.out.println("success");
				// System.out.println("");
				validSolutions++;
				System.out.println("");
				System.out.println("Solutions found: " + validSolutions);
				g.show(slottedGrid);
				n = g.nextNum(n);
				// for (int j = 0; j < 81; j++) {
				// System.out.print(n[80 - j]);
				// }
				// System.out.println("");
				n[g.getZeros()]++;
				if (validSolutions > 1) {
					System.out.println("Too many solutions, restarting..");
					create(lowerBound, upperBound);
					return;
				} else {
					System.out.print("Looking for solutions");
				}
			}
		}

		g.show(g.get());

		// int max = 0;
		//
		// // System.out.println(6%3);
		// for (int i = 0; i < 9; i++) {
		// for (int j = 0; j < 9; j++) {
		// if (g.get()[i][j] == 0) {
		// int[][] newG = g.insertRand(g.get(), i, j);
		// if (newG != null) {
		// g.set(newG);
		// // g.show(newG);
		// } else {
		// if (i * 9 + j > max) {
		// max = i * 9 + j;
		// System.out.println(max + "/80");
		// }
		// i = 0;
		// j = -1;
		// g.init();
		// // count++;
		// // System.out.println(count);
		//
		// // g.show(g.get());
		// }
		// }
		// }
		// }
		// g.show(g.get());

		// grid.solve(grid.get(),0,false);
		// System.out.println(grid.isFull());
	}
}
