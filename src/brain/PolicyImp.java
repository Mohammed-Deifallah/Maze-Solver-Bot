package brain;

import java.util.Random;

import environment.Action;
import environment.Cell;

public class PolicyImp implements Policy{

	private Cell grid[][];
	private Action actions[][];
	private Double values[][];
	
	public PolicyImp(Cell grid[][]) {
		this.grid = grid;
	}
	
	@Override
	public void setRandActions() {
		int size = grid.length;
		actions = new Action[size][size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (grid[i][j].getPossibleActions().size() == 0) {
					actions[i][j] = Action.NONE;
				} else {
					int choice = random.nextInt(grid[i][j].getPossibleActions().size());
					actions[i][j] = grid[i][j].getPossibleActions().get(choice);
				}
			}
		}
	}
	
	@Override
	public void setActions(Action actions[][]) {
		this.actions = actions;	
	}
	
	@Override
	public Cell[][] getGrid() {
		return grid;	
	}
	
	@Override
	public boolean comparePolicy(Policy policy) {
		int size = grid.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (actions[i][j] != policy.getActions()[i][j] ) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Action[][] getActions() {
		return actions;
	}

	@Override
	public void setValues(Double[][] values) {
		this.values = values;
	}

	@Override
	public Double[][] getValues() {
		return values;
	}

	@Override
	public void setCells(Cell[][] cell) {
		this.grid = cell;
	}
	
}
