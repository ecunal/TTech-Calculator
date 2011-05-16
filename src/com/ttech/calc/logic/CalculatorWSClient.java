package com.ttech.calc.logic;

/**
 * 
 * Logic class of the calculator application. CalculatorWSClient uses web
 * services to handle caculator's requests and provides methods for performing
 * calculations.
 * 
 * @author ecem
 * 
 */

public class CalculatorWSClient {

	private static TTechWebServiceImplService service;
	private static TTechWebService port;

	public static final int ADD = 0, SUB = 1, MUL = 2, DIV = 3, NONE = -1;

	private double lvalue, rvalue, digitNo;
	private int lastOp;
	private boolean dot;

	/**
	 * Constructor of CalculatorWSClient class. Both initializes the access to
	 * web services and loads calculator's initial values.
	 */

	public CalculatorWSClient() {

		service = new TTechWebServiceImplService();
		port = service.getTTechWebServiceImplPort();

		lvalue = 0.0;
		rvalue = 0.0;
		digitNo = 1.0;
		lastOp = NONE;
		dot = false;

	}

	/**
	 * The action handler when '=' (equate) button is activated. If there are no
	 * operations (e.g +, -, *, /) pressed before pressing '=' it takes no
	 * action, but if there is a waiting operation, operate() method performs
	 * the waiting operation and prepares calculator for a new operation.
	 * 
	 * @return lvalue, the result of the operation performed.
	 */

	public Double operate() {

		try {
			switch (lastOp) {
			case NONE:
				break;
			case ADD:
				lvalue = port.add(lvalue, rvalue);
				break;
			case SUB:
				lvalue = port.subtract(lvalue, rvalue);
				break;
			case MUL:
				lvalue = port.multiply(lvalue, rvalue);
				break;
			case DIV:
				lvalue = port.divide(lvalue, rvalue);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rvalue = 0.0;
		resetD();

		return lvalue;
	}

	/**
	 * Action handler when an operation (+, -, *, /) button is pressed. If there
	 * is a waiting operation before pressing this button, first the pending
	 * operation is completed, then the method changes calculator's state. After
	 * changing state of the calculator, it prepares calculator for a new input
	 * number.
	 * 
	 * @param op
	 *            {numeric value for an operation}
	 * @return lvalue, the number entered before pressing any of the operation
	 *         buttons.
	 */

	public double operationPressed(int op) {

		if (lastOp != NONE) {
			lvalue = operate();
			lastOp = NONE;
		}

		switch (op) {
		case ADD:
			lastOp = ADD;
			break;
		case SUB:
			lastOp = SUB;
			break;
		case DIV:
			lastOp = DIV;
			break;
		case MUL:
			lastOp = MUL;
			break;
		}

		resetD();
		return lvalue;
	}

	/**
	 * 
	 * The action handler when an number (0-9) is pressed. According to the
	 * previously entered inputs and numbers, it updates the current value.
	 * 
	 * @param num
	 * @return The value which is currently being edited. If there are no
	 *         operation buttons pressed beforehand, lvalue will be returned,
	 *         but if there is an operation pending, rvalue will be returned.
	 */

	public double numberPressed(int num) {

		double dnum = num * digitNo;

		if (dot)
			digitNo /= 10;

		if (lastOp == NONE) {
			if (!dot)
				lvalue *= 10;
			if (lvalue < 0)
				dnum *= -1;
			lvalue += dnum;
			return lvalue;
		}

		if (!dot)
			rvalue *= 10;
		if (rvalue < 0)
			dnum *= -1;
		rvalue += dnum;
		return rvalue;

	}

	/**
	 * If the dot button is pressed, the function changes the multiplier of the
	 * number that is currently being entered. However if the dot button is
	 * pressed before while entering the current input, it ignores the second
	 * dot button.
	 * 
	 * @return The value which is currently being edited. If there are no
	 *         operation buttons pressed beforehand, lvalue will be returned,
	 *         but if there is an operation pending, rvalue will be returned.
	 */

	public double dotPressed() {

		if (!dot) {
			dot = true;
			digitNo = 0.1;
		}

		if (lastOp == NONE)
			return lvalue;
		return rvalue;
	}

	/**
	 * The method preparing the calculator for a new number input. Resets the
	 * location of the dot and the digit number that is currently being entered.
	 */
	public void resetD() {
		dot = false;
		digitNo = 1;
	}

	/**
	 * Performs the action when "Clear" button is pressed. Resets the situation
	 * of the calculator to the initial point.
	 * 
	 * @return lvalue, which is recently reseted to zero.
	 */
	public double resetAll() {
		lastOp = NONE;
		lvalue = rvalue = 0;
		resetD();
		return lvalue;
	}

}
