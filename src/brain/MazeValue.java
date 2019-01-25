package brain;

import java.awt.Point;

import environment.Action;
import environment.Cell;

public class MazeValue implements Value {

	private Cell[][] maze;
	private double[][] values;

	public MazeValue(Cell[][] grid) {
		this.maze = grid;
		values = new double[grid.length][grid.length];
		setValues(grid);
	}

	@Override
	public void setValues(Cell[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				values[i][j] = grid[i][j].getV();
			}
		}
	}

	@Override
	public Cell[][] getGrid() {
		return maze;
	}

	@Override
	public double[][] getValues() {
		return values;
	}

	// Value Iteration
	@Override
	public double[][] updateValues(double gamma) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				Cell current = maze[i][j];
				double maximum_val = Double.MIN_VALUE;
				int reward = current.getReward();
				for (Action action : current.getPossibleActions()) {
					Point next_cell = action.getTransition(current);
					double new_val = reward + gamma * maze[next_cell.x][next_cell.y].getV();
					if (new_val > maximum_val) {
						new_val = maximum_val;
					}
				}
				current.setV(maximum_val);
				values[i][j] = maximum_val;
			}
		}
		return values;
	}

}
