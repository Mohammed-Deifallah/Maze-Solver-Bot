package environment;

import java.util.Random;

public class Grid {
	private Cell grid[][];
	private int size;

	public Grid(int n, double prob) {
		grid = new Cell[n][n];
		size = n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (0 < i && i < n - 1 && 0 < j && j < n - 1) {
					grid[i][j] = new Cell(i, j, Subtype.MIDDLE);
				} else if ((i == 0 && j == 0) || (i == n - 1 && j == n - 1) || (i == 0 && j == n - 1)
						|| (i == n - 1 && j == 0)) {
					grid[i][j] = new Cell(i, j, Subtype.CORNER);
				} else {
					grid[i][j] = new Cell(i, j, Subtype.EDGE);
				}
			}
		}
		grid[0][0].setType(Type.START);
		grid[n -1][n - 1].setType(Type.END);
		initGrid(prob);
	}

	public Cell[][] getGrid() {
		return this.grid.clone();
	}

	public int getSize() {
		return size;
	}

	private void initGrid(double r) {
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j].getType() == Type.OPEN) {
					double prob = random.nextDouble();
					if (prob < r) {
						grid[i][j].setType(Type.BARRIER);
					}
				}
			}
		}
	}
}
