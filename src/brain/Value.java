package brain;

import environment.Cell;

public interface Value {
	void setValues(Cell grid[][]);

	Cell[][] getGrid();

	double[][] getValues();

	public double[][] updateValues(double gamma);
}
