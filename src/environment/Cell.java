package environment;

import java.awt.Point;
import java.util.ArrayList;

public class Cell {
	private int r, c;
	private Type cell_type;
	private Subtype cell_subtype;
	private Action cell_action;
	private double value;
	private ArrayList<Action> possible_actions;
	private Point position;

	public Cell(int row, int column, Subtype subtype) {
		this.r = row;
		this.c = column;
		this.position = new Point(r, c);
		this.cell_type = Type.OPEN;
		this.cell_subtype = subtype;
		possible_actions = new ArrayList<>();
		fillPossibleActions();
	}

	public void setType(Type type) {
		this.cell_type = type;
	}

	public void setAction(Action action) {
		this.cell_action = action;
	}

	public Action getAction() {
		return cell_action;
	}

	public Type getType() {
		return cell_type;
	}

	public ArrayList<Action> getPossibleActions() {
		return possible_actions;
	}

	public void setV(double v) {
		this.value = v;
	}

	public double getV() {
		return value;
	}

	public Cell clone() {
		Cell cell = new Cell(r, c, cell_subtype);
		cell.setType(cell_type);
		return cell;
	}

	public int getReward() {
		if (cell_type == Type.BARRIER) {
			return Integer.MIN_VALUE;
		} else if (cell_type == Type.END) {
			return 1000;
		}
		return -1;
	}

	public Point getPosition() {
		return position;
	}

	private void fillPossibleActions() {
		if (cell_type == Type.BARRIER) {
			return;
		}
		if (cell_subtype == Subtype.MIDDLE) {
			possible_actions.add(Action.EAST);
			possible_actions.add(Action.NORTH);
			possible_actions.add(Action.WEST);
			possible_actions.add(Action.SOUTH);
		} else if (cell_subtype == Subtype.CORNER) {
			if (r == 0 && c == 0) {
				possible_actions.add(Action.EAST);
				possible_actions.add(Action.SOUTH);
			} else if (r == c) {
				possible_actions.add(Action.NORTH);
				possible_actions.add(Action.WEST);
			} else if (r == 0) {
				possible_actions.add(Action.SOUTH);
				possible_actions.add(Action.WEST);
			} else {
				possible_actions.add(Action.EAST);
				possible_actions.add(Action.NORTH);
			}
		} else {
			if (r == 0) {
				possible_actions.add(Action.EAST);
				possible_actions.add(Action.WEST);
				possible_actions.add(Action.SOUTH);
			} else if (c == 0) {
				possible_actions.add(Action.EAST);
				possible_actions.add(Action.NORTH);
				possible_actions.add(Action.SOUTH);
			} else if (r > c) {
				possible_actions.add(Action.EAST);
				possible_actions.add(Action.NORTH);
				possible_actions.add(Action.WEST);
			} else {
				possible_actions.add(Action.NORTH);
				possible_actions.add(Action.WEST);
				possible_actions.add(Action.SOUTH);
			}
		}
	}

}
