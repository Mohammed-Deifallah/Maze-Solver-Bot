package brain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import environment.Cell;
import environment.Grid;

public class ValueIterationImp implements ValueIteration{
	
	List<Double[][]> values;
	double gamma;
	Grid grid;
	
	public ValueIterationImp (double gamma) {
		this.values = new ArrayList<Double[][]>();
		this.gamma = gamma;
	}

	@Override
	public void value_iteration(Grid grid) {
		int n = grid.getSize();
		this.grid = grid;
		values.clear();
		Double[][] state_values = new Double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				state_values[i][j] = 0.0d;
			}
		}
		double diff = 1;
		while (diff > 1e-7) {
			diff = 0.0d;
			values.add(state_values);
			Double[][] new_state_values = new Double[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					Cell cell = grid.getGrid()[i][j];
					double maxVal = -Double.MAX_VALUE;
					for (int k = 0; k < cell.getPossibleActions().size(); k++) {
						Point nextState = cell.getPossibleActions().get(k).getTransition(cell);
						double val = cell.getReward() + this.gamma * state_values[nextState.x][nextState.y];
						if (val > maxVal) {
							maxVal = val;
						}
					}
					if (cell.getPossibleActions().size() == 0) {
						maxVal = cell.getReward();
					}
					diff += Math.abs(state_values[i][j] - maxVal);
					new_state_values[i][j] = maxVal;
				}
			}
			state_values = new_state_values;
		}
	}

	@Override
	public List<Double[][]> getValueIterations() {
		return values;
	}

	@Override
	public Policy getOptimalPolicy() {
		PolicyImprovement improver = new PolicyImprovement();
		return improver.getGreedyPolicy(values.get(values.size() - 1), this.grid, this.gamma);
	}
}
