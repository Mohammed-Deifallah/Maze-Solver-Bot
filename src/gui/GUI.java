package gui;

import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import brain.PolicyIteration;
import brain.ValueIteration;
import brain.ValueIterationImp;
import environment.Grid;

public class GUI extends JFrame {

	private Grid grid;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(10, 10, 500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		grid = new Grid(4, 0.2);
		ValueIteration valueSolver = new ValueIterationImp(1);
		valueSolver.value_iteration(grid);
		Double[][] values = valueSolver.getValueIterations().get(valueSolver.getValueIterations().size() - 1);
		MazeBoard board = new MazeBoard(grid.getSize());
		ValueBoard board2 = new ValueBoard(grid.getSize());
		PolicyBoard board3 = new PolicyBoard(grid.getSize());
		board.updateLabels(grid);
		board2.updateLabels(values);
		board3.updateLabels(valueSolver.getOptimalPolicy().getActions());
		this.add(board);
		this.add(Box.createVerticalGlue());
		this.add(board2);
		this.add(Box.createVerticalGlue());
		this.add(board3);
		this.add(Box.createVerticalGlue());
		PolicyIteration policySolver = new PolicyIteration(0.1);
		policySolver.policy_iteration(grid);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		System.out.println(policySolver.getPolicies().size());
		for (int i = 0; i < policySolver.getPolicies().size(); i++) {
			PolicyBoard minBoard = new PolicyBoard(grid.getSize());
			minBoard.updateLabels(policySolver.getPolicies().get(i).getActions());
			panel.add(minBoard);
			panel.add(Box.createVerticalGlue());
		}
		JScrollPane pane = new JScrollPane(panel);
		this.add(pane);
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

}
