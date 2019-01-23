package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import environment.Grid;

public class GUI extends JFrame {

	private Grid grid;
	private JTextField[][] board;
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
		setBounds(10, 10, 500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		grid = new Grid(4, .5);
		board = new JTextField[4][4];

		for (int i = 0, x = 20; i < 4; i++, x += 50) {
			for (int j = 0, y = 20; j < 4; j++, y += 50) {
				board[i][j] = new JTextField();
				board[i][j].setBounds(y, x, 50, 50);
				if (grid.getGrid()[i][j].getType() == environment.Type.BARRIER) {
					board[i][j].setText("B");
				} else {
					board[i][j].setText("O");
				}
				getContentPane().add(board[i][j]);
			}
		}
		board[0][0].setText("S");
		board[3][3].setText("T");
	}

}
