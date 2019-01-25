package brain;

import environment.Action;
import environment.Cell;

public interface Policy {
	boolean comparePolicy(Policy policy);
	void setRandActions();
	void setActions(Cell grid[][]);
	Cell[][] getGrid();
	Action[][] getActions();
}
