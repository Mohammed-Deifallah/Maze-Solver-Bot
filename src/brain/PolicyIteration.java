package brain;

import java.util.ArrayList;
import java.util.List;

import environment.Grid;

public class PolicyIteration {
	List<Double[][]> values;
	List<Policy> policies;
	double gamma;
	Grid grid;
	
	public PolicyIteration (double gamma) {
		this.values = new ArrayList<Double[][]>();
		this.policies = new ArrayList<Policy>();
		this.gamma = gamma;
	}
	
	public void policy_iteration(Grid grid) {
		Policy pol = new PolicyImp(grid.getGrid());
		PolicyEvaluation evaluater = new PolicyEvaluation();
		PolicyImprovement improver = new PolicyImprovement();
		pol.setRandActions();
		while(policies.size() < 2 || !policies.get(policies.size() - 1).comparePolicy(
				policies.get(policies.size() - 2))) {
			Double[][] vals = evaluater.evaluatePolicy(grid, pol, gamma);
			pol.setValues(vals);
			this.policies.add(pol);
			this.values.add(vals);
			pol = improver.getGreedyPolicy(vals, grid, gamma);
		}
	}
	
	public List<Double[][]> getValues() {
		return values;
	}
	
	public List<Policy> getPolicies() {
		return policies;
	}
}
