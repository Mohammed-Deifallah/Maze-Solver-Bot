package brain;

import java.util.Random;

import environment.Cell;
import environment.Utils;
public class PolicyImp implements Policy{

	private Cell grid[][];
	
	public PolicyImp(Cell grid[][]) {
		this.grid = grid;
	}
	
	@Override
	public void setRandActions() {
		int size = grid.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Random random = new Random(4);
				grid[i][j].setAction(Utils.selectAction(random.nextInt()));
			}
		}	
	}
	
	@Override
	public void setActions(Cell grid[][]) {
		this.grid = grid;	
	}
	
	@Override
	public Cell[][] getGrid() {
		return grid;	
	}
	
	@Override
	public boolean comparePolicy(Policy policy) {
		Cell newGrid[][] = policy.getGrid();
		int size = grid.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (newGrid[i][j].getAction() != grid[i][j].getAction() ) {
					return false;
				}
			}
		}
		return true;
	}
	
}
