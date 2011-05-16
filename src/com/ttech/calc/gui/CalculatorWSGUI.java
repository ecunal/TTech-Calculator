package com.ttech.calc.gui;

import com.ttech.calc.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The GUI class which graphically represents the work of CalculatorWSClient
 * class.
 * 
 * @author ecem
 * 
 */

public class CalculatorWSGUI implements ActionListener {

	public static final int ADD = 0, SUB = 1, MUL = 2, DIV = 3, NONE = -1;

	private CalculatorWSClient c = new CalculatorWSClient();

	private JFrame calcFrame;
	private JPanel[] panels;
	private JButton[] numButtons;
	private JTextField text;
	private JButton resetB, divB, mulB, subB, dotButton, addButton,
			equateButton;

	/**
	 * Constructor of GUI class, initializes the contents of the GUI (buttons,
	 * text fields etc).
	 */

	public CalculatorWSGUI() {

		calcFrame = new JFrame("Calculator");
		panels = new JPanel[5];
		text = new JTextField();
		numButtons = new JButton[10];
		divB = new JButton("/");
		mulB = new JButton("*");
		subB = new JButton("-");
		dotButton = new JButton(".");
		addButton = new JButton("+");
		equateButton = new JButton("=");
		resetB = new JButton("C");

	}

	/**
	 * Adds buttons and text fields to the main frame, sets their locations and
	 * actions using setButtonDimensions, addTextPanel, fillPanel1, fillPanel2,
	 * fillPanel3 and fillPanel4 functions.
	 * 
	 */

	public void buildGUI() {

		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setLocation(400, 200);

		JPanel contentPane = (JPanel) calcFrame.getContentPane();

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
		}

		for (int i = 0; i < numButtons.length; i++) {
			numButtons[i] = new JButton("" + i);
			numButtons[i].setPreferredSize(new Dimension(50, 30));
			numButtons[i].setActionCommand("" + i);
			numButtons[i].addActionListener(this);
		}

		setButtonDimensions();
		addTextPanel();
		fillPanel1();
		fillPanel2();
		fillPanel3();
		fillPanel4();

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		for (JPanel panel : panels)
			contentPane.add(panel);

		setActions();

		calcFrame.pack();
		calcFrame.setVisible(true);
	}

	private void addTextPanel() {

		text.setColumns(20);
		text.setText("0");
		text.setHorizontalAlignment(JTextField.RIGHT);
		panels[0].add(text);
	}

	private void fillPanel1() {

		panels[1].setLayout(new FlowLayout(FlowLayout.LEFT));
		panels[1].add(numButtons[7]);
		panels[1].add(numButtons[8]);
		panels[1].add(numButtons[9]);
		panels[1].add(divB);
	}

	private void fillPanel2() {

		panels[2].setLayout(new FlowLayout(FlowLayout.LEFT));
		panels[2].add(numButtons[4]);
		panels[2].add(numButtons[5]);
		panels[2].add(numButtons[6]);
		panels[2].add(mulB);
	}

	private void fillPanel3() {

		panels[3].setLayout(new FlowLayout(FlowLayout.LEFT));
		panels[3].add(numButtons[1]);
		panels[3].add(numButtons[2]);
		panels[3].add(numButtons[3]);
		panels[3].add(subB);
	}

	private void fillPanel4() {

		panels[4].setLayout(new FlowLayout(FlowLayout.LEFT));
		panels[4].add(numButtons[0]);
		panels[4].add(dotButton);
		panels[4].add(resetB);
		panels[4].add(addButton);
		panels[4].add(equateButton);
	}

	private void setActions() {

		addButton.setActionCommand("ADD");
		addButton.addActionListener(this);
		subB.setActionCommand("SUBTRACT");
		subB.addActionListener(this);
		mulB.setActionCommand("MULTIPLY");
		mulB.addActionListener(this);
		divB.setActionCommand("DIVIDE");
		divB.addActionListener(this);

		resetB.setActionCommand("RESET");
		resetB.addActionListener(this);
		dotButton.setActionCommand("DOT");
		dotButton.addActionListener(this);
		equateButton.setActionCommand("EQUATE");
		equateButton.addActionListener(this);

	}

	private void setButtonDimensions() {

		resetB.setPreferredSize(new Dimension(50, 30));
		divB.setPreferredSize(new Dimension(50, 30));
		mulB.setPreferredSize(new Dimension(50, 30));
		subB.setPreferredSize(new Dimension(50, 30));
		dotButton.setPreferredSize(new Dimension(50, 30));
		addButton.setPreferredSize(new Dimension(50, 30));
		equateButton.setPreferredSize(new Dimension(50, 30));

	}

	/**
	 * Associates the buttons with the required actions. Calls the functions
	 * from CalculatorWSClient logic class.
	 */
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand == null || actionCommand.trim().length() <= 0) {
			return;
		}

		int number = -1;
		try {
			number = Integer.parseInt(actionCommand);
		} catch (NumberFormatException en) {
		}

		if (number >= 0)
			text.setText("" + (c.numberPressed(number)));
		else {

			if (actionCommand.equals("ADD"))
				text.setText("" + c.operationPressed(ADD));
			else if (actionCommand.equals("SUBTRACT"))
				text.setText("" + c.operationPressed(SUB));
			else if (actionCommand.equals("DIVIDE"))
				text.setText("" + c.operationPressed(DIV));
			else if (actionCommand.equals("MULTIPLY"))
				text.setText("" + c.operationPressed(MUL));

			else if (actionCommand.equals("DOT"))
				text.setText("" + c.dotPressed());
			else if (actionCommand.equals("EQUATE"))
				text.setText("" + c.operate());
			else if (actionCommand.equals("RESET"))
				text.setText("" + c.resetAll());
		}
	}

	public static void main(String[] args) {
		CalculatorWSGUI gui = new CalculatorWSGUI();
		gui.buildGUI();
	}

}
