package brain;

import java.awt.Point;

import environment.Action;
import environment.Cell;
import environment.Grid;

public class PolicyImprovement {
	Policy getGreedyPolicy(Double[][] vals, Grid grid, Double gamma) {
		Policy pol = new PolicyImp(grid.getGrid());
		pol.setValues(vals);
		Action actions[][] = new Action[grid.getSize()][grid.getSize()];
		int n = grid.getSize();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				actions[i][j] = Action.NONE;
				Cell cell = grid.getGrid()[i][j];
				double maxVal = -Double.MAX_VALUE;
				for (int k = 0; k < cell.getPossibleActions().size(); k++) {
					Point nextState = cell.getPossibleActions().get(k).getTransition(cell);
					double val = cell.getReward() + gamma * vals[nextState.x][nextState.y];
					if (val > maxVal) {
						maxVal = val;
						actions[i][j] = cell.getPossibleActions().get(k);
					}
				}
			}
		}
		pol.setActions(actions);
		return pol;
	}
}
