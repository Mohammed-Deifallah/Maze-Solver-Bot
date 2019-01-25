package brain;

import java.util.ArrayList;
import java.util.Random;

import environment.Action;
import environment.Cell;

public class MazePolicy implements Policy {

	private Cell[][] maze;
	private Action[][] actions;

	public MazePolicy(Cell[][] grid) {
		this.maze = grid;
		setRandActions();
	}

	@Override
	public boolean comparePolicy(Policy policy) {
		Action[][] tempActions = policy.getActions();
		if (maze.length != tempActions.length) {
			return false;
		}
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (actions[i][j] != tempActions[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void setRandActions() {
		Random rand = new Random();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				ArrayList<Action> possibleActions = maze[i][j].getPossibleActions();
				actions[i][j] = possibleActions.get(rand.nextInt(possibleActions.size()));
			}
		}
	}

	@Override
	public void setActions(Cell[][] grid) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				actions[i][j] = maze[i][j].getAction();
				/*
				 * Cell current = maze[i][j]; if (current.getType() ==
				 * Type.BARRIER) { continue; } double maximum =
				 * Double.MIN_VALUE; ArrayList<Action> actions =
				 * current.getPossibleActions(); Action max_action =
				 * actions.get(0); int r = current.getReward(); for (Action
				 * action : actions) { Point next =
				 * action.getTransition(current); double v = gamma *
				 * maze[next.x][next.y].getV(); if (r + v > maximum) { maximum =
				 * r + v; max_action = action; } }
				 * maze[i][j].setAction(max_action);
				 */
			}
		}
	}

	@Override
	public Cell[][] getGrid() {
		return maze;
	}

	@Override
	public Action[][] getActions() {
		return actions;
	}

}
