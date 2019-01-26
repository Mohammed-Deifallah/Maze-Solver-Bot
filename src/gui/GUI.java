package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import brain.PolicyIteration;
import brain.ValueIteration;
import brain.ValueIterationImp;
import environment.Grid;

public class GUI extends JFrame {

	private Grid grid;
	private JTextField input_size, input_prob, input_gamma;
	private JButton start;
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
		getContentPane().setLayout(null);

		input_size = new JTextField();
		initialize_text_field(input_size, "Grid Size", 250, 300);

		input_prob = new JTextField();
		initialize_text_field(input_prob, "Randomization Probability", 550, 300);

		input_gamma = new JTextField();
		initialize_text_field(input_gamma, "Discount Factor", 850, 300);

		start = new JButton("Simulate");
		start.setBackground(Color.GRAY);
		start.setForeground(Color.WHITE);
		start.setBounds(625, 400, 100, 50);
		this.getContentPane().add(start);
		JFrame myFrame = this;
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt(input_size.getText().trim());
				double prob = Double.parseDouble(input_prob.getText().trim());
				double gamma = Double.parseDouble(input_gamma.getText().trim());
				if (size < 2) {
					JOptionPane.showMessageDialog(myFrame, "Enter a valid maze size. must be 2 or bigger");
					return;
				}
				if (prob >= 1 || prob < 0) {
					JOptionPane.showMessageDialog(myFrame, "Enter a valid block probability, must be between 0(inclusive) and 1(exclusive)");
					return;
				}
				if (gamma < 0 || gamma >= 1) {
					JOptionPane.showMessageDialog(myFrame, "Enter a valid discount factor, must be between 0(inclusive) and 1(exclusive)");
					return;
				}
				input_size.setVisible(false);
				input_prob.setVisible(false);
				input_gamma.setVisible(false);
				start.setVisible(false);
				getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

				grid = new Grid(size, prob);
				ValueIteration valueSolver = new ValueIterationImp(gamma);
				long start = System.currentTimeMillis(); 
				valueSolver.value_iteration(grid);
				long end = System.currentTimeMillis(); 
				MazeBoard board = new MazeBoard(grid.getSize());
				ValueIterationPanel valuePanel = new ValueIterationPanel(valueSolver.getValueIterations(),
						valueSolver.getOptimalPolicy().getActions(), end - start);
				board.updateLabels(grid);
				add(board);
				add(Box.createVerticalGlue());
				add(valuePanel);
				add(Box.createVerticalGlue());
				
				board.setVisible(true);
				valuePanel.setVisible(true);
				PolicyIteration policySolver = new PolicyIteration(gamma);
				start = System.currentTimeMillis(); 
				policySolver.policy_iteration(grid);
				end = System.currentTimeMillis(); 
				PolicyIterationPanel policyPanel = new PolicyIterationPanel(policySolver.getPolicies(),
						policySolver.getValues(), end - start);
				add(policyPanel);
			}
		});

		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.DARK_GRAY);
	}

	private void initialize_text_field(JTextField field, String name, int x, int y) {
		field.setForeground(Color.LIGHT_GRAY);
		field.setText(name);
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setFont(new Font("Hobo Std", Font.ROMAN_BASELINE, 20));
		field.setBounds(x, y, 250, 50);
		field.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		field.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (field.getText().equals("")) {
					field.setText(name);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (field.getText().equals(name)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}
		});
		this.getContentPane().add(field);
	}

}
