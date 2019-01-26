package brain;

import environment.Action;
import environment.Cell;

public interface Policy {
	
	boolean comparePolicy(Policy policy);
	
	void setRandActions();
	
	void setCells(Cell cell[][]);
	void setActions(Action grid[][]);
	void setValues(Double values[][]);
	
	Cell[][] getGrid();
	Double[][] getValues();
	Action[][] getActions();
}
