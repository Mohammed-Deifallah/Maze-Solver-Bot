package environment;

import java.util.HashSet;
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
		initGrid((int) ((n * n - 2) * prob));

	}

	public Grid(Grid grid2) {
		/*
		 * grid = grid2.getGrid(); size = grid.length;
		 */
	}

	public Cell[][] getGrid() {
		return this.grid.clone();
	}

	public Grid clone() {
		/*
		 * Grid grid2 = this; return grid2;
		 */
		return null;
	}

	public int getSize() {
		return size;
	}

	private void initGrid(int r) {
		HashSet<Integer> indices = new HashSet<>();
		Random random = new Random();
		while (indices.size() < r) {
			// from (0, 1) to (n - 1, n - 2)
			indices.add(random.nextInt((size * size - 2)) + 1);
		}
		for (Integer i : indices) {
			grid[i / size][i % size].setType(Type.BARRIER);
		}
		indices.clear();
	}
}
