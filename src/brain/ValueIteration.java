package brain;

import java.util.List;

import environment.Grid;

public interface ValueIteration {
	
	void value_iteration(Grid grid);
	List<Double[][]> getValueIterations();
	Policy getOptimalPolicy();
}
