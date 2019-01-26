package brain;

import java.awt.Point;

import environment.Action;
import environment.Cell;
import environment.Grid;

public class PolicyEvaluation {
	public Double[][] evaluatePolicy(Grid grid, Policy pol, Double gamma) {
		int n = grid.getSize();
		Double[][] state_values = new Double[n][n];
		Action[][] actions = pol.getActions();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				state_values[i][j] = 0.0d;
			}
		}
		
		double diff = 1;
		int num_loops = 0;
		while (diff > 1e-3 && num_loops < 1e9) {
			diff = 0.0d;
			num_loops++;
			Double[][] new_state_values = new Double[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					Cell cell = grid.getGrid()[i][j];
					Action action = actions[i][j];
					double maxVal = -Double.MAX_VALUE;
					if (action == Action.NONE) {
						maxVal = cell.getReward();
					} else {
						Point nextState = action.getTransition(cell);
						maxVal = cell.getReward() + gamma * state_values[nextState.x][nextState.y];
					}
					diff += Math.abs(state_values[i][j] - maxVal);
					new_state_values[i][j] = maxVal;
				}
			}
			state_values = new_state_values;
		}
		return state_values;
	}
}
