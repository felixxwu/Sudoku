import java.io.IOException;

//import java.util.Scanner;

public class Sudoku {
	public static void main(String[] args) throws IOException {
		System.out.print("Open a .csv that contains your sudoku in the dialogue...");
		Grid g = new Grid();
		g.init();
		int attempts = 0;

		int[] n = new int[81];
		for (int i = 0; i < 81; i++) {
			n[i] = 1;
		}
		int i = 0;
		while (i < 1) {

			// Scanner scanner = new Scanner(System.in);
			// String x = scanner.next();
			int[][] slottedGrid = g.slotIntoGrid(n);
			if (slottedGrid[0][0] == 100) {
				int pos = slottedGrid[0][1];
				// System.out.println("error " + pos);
				n[pos]++;
				n = g.checkOver9(n);
			} else if (slottedGrid[0][0] == 200) {
//				int pos = slottedGrid[0][1];
				// System.out.println("too high " + pos);
				n = g.otherCheckOver9(n);
				attempts++;
				if (attempts % 3000 == 0) {
					int[] display = new int[g.getZeros()];
					for (int j = 0; j < g.getZeros(); j++) {
						display[j] = n[80 - j];
					}
					int k = g.getZeros() - 1;
					while (display[k] == 1 && k > 0) {
						display[k] = 0;
						k--;
					}
					for(int j = 0; j < g.getZeros(); j++) {
						if(display[j] == 0) {
							System.out.print("-");
						} else {
							System.out.print(display[j]);
						}
					}
						System.out.println("");
				}
			} else {

				// System.out.println("success");
				// System.out.println("");
				g.show(slottedGrid);
				System.out.println("DONE!");
				n = g.nextNum(n);
				i++;
			}
		}

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
