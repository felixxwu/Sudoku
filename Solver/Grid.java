import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Grid {
	private int[][] grid = new int[9][9];
	private int min = 80;
	private int zeros;
	// private int attempts = 0;

	public Grid() {

	}

	public int getZeros() {
		return this.zeros;
	}

	public void show(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					System.out.print("-");
				} else {
					System.out.print(grid[i][j]);
				}
				if (j == 2 || j == 5) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
			if (i == 2 || i == 5) {
				for (int k = 0; k < 17; k++) {
					System.out.print("-");
				}
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("");
	}

	public void initRandom() {

		int[][] newG = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newG[i][j] = 0;
			}
		}

		int count = 0;
		int low = 17;
		int high = 40;
		int limit = low + (int) (Math.random() * (high - low));

		while (count < limit) {
			int x = (int) (Math.random() * 9);
			int y = (int) (Math.random() * 9);
			if (newG[x][y] != 0) {
				newG[x][y] = (int) (Math.random() * 8 + 1);
				count++;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.grid[i][j] = newG[i][j];
			}
		}

		show(this.grid);

		zeros = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.grid[i][j] == 0) {
					zeros++;
				}
			}
		}
	}

	public void init() throws IOException {
		// int[][] newG = { { 0, 5, 0, 0, 0, 0, 9, 4, 0 }, { 0, 0, 2, 4, 0, 0,
		// 0, 1, 0 }, { 0, 4, 0, 3, 1, 8, 5, 0, 6 },
		// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 3, 0, 6, 4 }, { 0, 0,
		// 3, 0, 8, 2, 0, 0, 0 },
		// { 5, 8, 0, 0, 3, 9, 6, 0, 0 }, { 2, 0, 0, 8, 0, 0, 0, 0, 0 }, { 0, 0,
		// 0, 0, 2, 6, 0, 9, 0 } };

		// int[][] newG = { {0,0,2,0,0,0,7,1,0},
		// {0,0,0,6,7,0,0,0,0},
		// {0,1,0,8,0,5,3,9,6},
		// {0,3,1,0,0,0,0,0,0},
		// {9,0,0,7,0,4,0,0,3},
		// {0,0,0,0,0,0,8,4,0},
		// {1,4,3,5,0,8,0,7,0},
		// {0,0,0,0,3,9,0,0,0},
		// {0,8,9,0,0,0,5,0,0}};

		// int[][] newG = { {0,0,0,0,0,0,0,0,0},
		// {0,0,0,0,0,3,0,8,5},
		// {0,0,1,0,2,0,0,0,0},
		// {0,0,0,5,0,7,0,0,0},
		// {0,0,4,0,0,0,1,0,0},
		// {0,9,0,0,0,0,0,0,0},
		// {5,0,0,0,0,0,0,7,3},
		// {0,0,2,0,1,0,0,0,0},
		// {0,0,0,0,4,0,0,0,9}};

		// int[][] newG = { { 0, 0, 0, 0, 5, 0, 0, 0, 0 }, { 9, 0, 6, 0, 0, 0,
		// 3, 0, 7 }, { 0, 0, 0, 4, 0, 9, 0, 0, 0 },
		// { 0, 1, 0, 0, 0, 0, 0, 5, 0 }, { 2, 0, 0, 6, 0, 7, 0, 0, 1 }, { 0, 4,
		// 0, 0, 0, 0, 0, 9, 0 },
		// { 0, 0, 0, 7, 0, 1, 0, 0, 0 }, { 7, 0, 9, 0, 0, 0, 2, 0, 6 }, { 0, 0,
		// 0, 0, 3, 0, 0, 0, 0 } };

		int[][] newG = new int[9][9];

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("C:/Users/felix/Downloads"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
		}

		String fileName = chooser.getSelectedFile().getPath();
		File file = new File(fileName);

		// this gives you a 2-dimensional array of strings
		List<List<String>> lines = new ArrayList<>();
		Scanner inputStream;

		try {
			inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				String line = inputStream.next();
				String[] values = line.split(",");
				// this adds the currently parsed line to the 2-dimensional
				// string array
				lines.add(Arrays.asList(values));
			}

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// the following code lets you iterate through the 2-dimensional array
		int lineNo = 1;
		for (List<String> line : lines) {
			int columnNo = 1;
			for (String value : line) {
				if (value.equals("")) {
					newG[lineNo - 1][columnNo - 1] = 0;
				} else {
					newG[lineNo - 1][columnNo - 1] = Integer.parseInt(value);
				}
				columnNo++;
			}
			lineNo++;
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.grid[i][j] = newG[i][j];
			}
		}

		show(this.grid);

		zeros = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.grid[i][j] == 0) {
					zeros++;
				}
			}
		}
	}

	public int[][] get() {
		return this.grid;
	}

	public void set(int[][] g) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.grid[i][j] = g[i][j];
			}
		}
	}

	public int[][] slotIntoGrid(int[] n) {
		int[][] slottedGrid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				slottedGrid[i][j] = this.grid[i][j];
			}
		}
		int npos = 80;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.grid[i][j] == 0) {
					slottedGrid[i][j] = n[npos];
					if (n[npos] == 10) {
						int[][] tooHigh = new int[1][2];
						tooHigh[0][0] = 200;
						tooHigh[0][1] = npos;
						// show(slottedGrid);
						return tooHigh;
					}
					if (!isCorrect(slottedGrid, i, j)) {
						int[][] notCorrect = new int[1][2];
						notCorrect[0][0] = 100;
						notCorrect[0][1] = npos;
						// show(slottedGrid);
						// System.out.println(npos);
						return notCorrect;
					}
					npos--;
					if (npos < this.min) {
						this.min = npos;
						// show(slottedGrid);
					}
				}
			}
		}
		return slottedGrid;
	}

	public int[][] insertRand(int[][] g, int x, int y) {
		int[][] newG = new int[9][9];

		// copy new grid
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newG[i][j] = g[i][j];
			}
		}

		boolean insertable = false;
		// int[] insertableNums = new int[9];
		// int inNumsPos = 0;
		for (int i = 1; i < 10; i++) {
			newG[x][y] = i;
			if (isCorrect(newG, x, y)) {
				insertable = true;
				// insertableNums[inNumsPos] = i;
				// System.out.println(i);
			}
		}
		if (!insertable) {
			return null;
		}
		while (true) {
			int n = (int) (Math.random() * 9 + 1);
			// System.out.println(n);
			newG[x][y] = n;
			if (isCorrect(newG, x, y)) {
				return newG;
			}
		}
	}

	public int[][] solve(int[][] newGrid, int layer, boolean done) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k = 0; k < 9;) { // add k++?
					int[][] newnewGrid = insert(newGrid, i, j, k);
					boolean isDone = check(solve(newnewGrid, layer - 1, done));
					show(newnewGrid);
					if (isDone) {
						return newnewGrid;
					} else {
						return null;
					}
				}
			}
		}
		return new int[9][9];
	}

	public void solve2() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

			}
		}
	}

	public int[] nextNum(int[] n) {
		int[] next = new int[81];
		for (int i = 0; i < 81; i++) {
			next[i] = n[i];
		}
		next[80] = next[80] + 1;
		next = checkOver9(next);
		return next;
	}

	public int[] checkOver9(int[] n) {
		int pos = 80;
		int[] newn = new int[81];
		for (int i = 0; i < 81; i++) {
			newn[i] = n[i];
		}
		while (newn[pos] >= 10) {
			newn[pos] = 1;
			newn[pos - 1] = newn[pos - 1] + 1;
			pos--;
		}
		return newn;
	}

	public int[] otherCheckOver9(int[] n) {
		if (n[80] == 10) {
			return null;
		}
		int[] newn = new int[81];
		for (int i = 0; i < 81; i++) {
			newn[i] = n[i];
		}
		for (int i = 0; i < 81; i++) {
			if (newn[i] == 10) {
				newn[i] = 1;
				newn[i + 1] = newn[i + 1] + 1;
			}
		}
		return newn;
	}

	public int[][] insert(int[][] grid, int x, int y, int n) {
		grid[x][y] = n;
		return grid;
	}

	public boolean isFull() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean check(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] != 0) {
					if (isCorrect(grid, i, j) != true) {
						// System.out.println(i + " " + j);
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean isCorrect(int[][] g, int x, int y) {
		int n = g[x][y];

		// check lines
		if (g[x][y] != 0) {
			for (int i = 0; i < 9; i++) { // vertical
				if (n == g[x][i] && i != y) {
					// System.out.println(n + " " + i + " " + x);
					return false;
				}
			}
			for (int i = 0; i < 9; i++) {
				if (n == g[i][y] && i != x) {
					// System.out.println(n + " " + i + " " + y);
					return false;
				}
			}
		}

		// check squares
		// 1
		int xmin = ((int) x / 3) * 3;
		int xmax = ((int) x / 3) * 3 + 3;
		int ymin = ((int) y / 3) * 3;
		int ymax = ((int) y / 3) * 3 + 3;
		// System.out.println(xmin + " " + xmax + " " + ymin + " " + ymax);
		for (int i = xmin; i < xmax; i++) {
			for (int j = ymin; j < ymax; j++) {
				if (n == g[i][j] && i != x && j != y) {
					// System.out.println(n + " " + i + " " + x);
					return false;
				}
			}
		}
		// System.out.println("correct" + " " + x + " " + y);
		return true;
	}
}
