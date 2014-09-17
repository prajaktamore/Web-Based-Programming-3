
import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculator
 */
public class BinaryCalc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String memory_save, finalmemoryvalue;
	static String op;
	static String operandone;

	public BinaryCalc() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String valueString = request.getParameter("value");
		String resultString = request.getParameter("result");

		String result = "";
		if (!resultString.isEmpty()) {
			result = resultString;
		}
		// memory_save = resultString;

		String value = valueString;

		Map parameters = request.getParameterMap();
		if (parameters.containsKey("plus")) {
			operandone = request.getParameter("result");
			result = value;
			request.setAttribute("value", result);
			op = "plus";
		} else if (parameters.containsKey("one")) {
			if (resultString.isEmpty() || resultString == null) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("1");
				request.setAttribute("value", stringBuilder);
			} else if (resultString.length() < 8) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(resultString);
				stringBuilder.append("1");
				request.setAttribute("value", stringBuilder);
			} else {
				request.setAttribute("value", resultString);
			}
		} else if (parameters.containsKey("zero")) {
			if (resultString.isEmpty() || resultString == null) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("0");
				request.setAttribute("value", stringBuilder);
			} else if (resultString.length() < 8) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(resultString);
				stringBuilder.append("0");
				request.setAttribute("value", stringBuilder);
			} else {
				request.setAttribute("value", resultString);
			}
		} else if (parameters.containsKey("minus")) {
			operandone = request.getParameter("result");
			result = value;
			request.setAttribute("value", result);
			op = "minus";
		} else if (parameters.containsKey("and")) {
			operandone = request.getParameter("result");
			result = value;
			request.setAttribute("value", result);
			op = "and";
		} else if (parameters.containsKey("or")) {
			operandone = request.getParameter("result");
			result = value;
			request.setAttribute("value", result);
			op = "or";
		} else if (parameters.containsKey("xor")) {
			operandone = request.getParameter("result");
			result = value;
			request.setAttribute("value", result);
			op = "xor";
		} else if (parameters.containsKey("memorysave")) {
			finalmemoryvalue = result;
		} else if (parameters.containsKey("memoryret")) {
			request.setAttribute("value", finalmemoryvalue);
		} else if (parameters.containsKey("memoryclear")) {
			finalmemoryvalue = "";
		} else if (parameters.containsKey("clear")) {
			request.setAttribute("value", "");
		} else if (parameters.containsKey("equal")) {
			String operation = op;
			if (operation.equalsIgnoreCase("plus")) {
				result = add(operandone, result);
				request.setAttribute("value", result);
			} else if (operation.equalsIgnoreCase("minus")) {
				result = subtract(operandone, result);
				request.setAttribute("value", result);
			} else if (operation.equalsIgnoreCase("and")) {
				result = and_oper(operandone, result);
				request.setAttribute("value", result);
			} else if (operation.equalsIgnoreCase("or")) {
				result = or_oper(operandone, result);
				request.setAttribute("value", result);
			} else if (operation.equalsIgnoreCase("xor")) {
				result = xor_oper(operandone, result);
				request.setAttribute("value", result);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("/ShowAll.jsp");
		view.forward(request, response);

	}

	public String and_oper(String str1, String str2) {
		if (str1 != null && str2 == null) {
			return str1;
		} else if (str1 == null && str2 != null) {
			return str2;
		} else if (str1 != null && str2 != null) {
			int d1 = Integer.parseInt(str1, 2);// Converting Binary to Decimal
			int d2 = Integer.parseInt(str2, 2);
			int i = (d1 & d2);
			String binaryAdd = Integer.toBinaryString(i);
			return binaryAdd;
		}
		return "";
	}

	public String or_oper(String str1, String str2) {
		if (str1 != null && str2 == null) {
			return str1;
		} else if (str1 == null && str2 != null) {
			return str2;
		} else if (str1 != null && str2 != null) {
			int d1 = Integer.parseInt(str1, 2);// Converting Binary to Decimal
			int d2 = Integer.parseInt(str2, 2);
			int i = (d1 | d2);
			String binaryAdd = Integer.toBinaryString(i);
			return binaryAdd;
		}
		return "";
	}

	public String xor_oper(String str1, String str2) {
		if (str1 != null && str2 == null) {
			return str1;
		} else if (str1 == null && str2 != null) {
			return str2;
		} else if (str1 != null && str2 != null) {

			int d1 = Integer.parseInt(str1, 2);// Converting Binary to Decimal
			int d2 = Integer.parseInt(str2, 2);
			int i = (d1 ^ d2);
			String binaryAdd = Integer.toBinaryString(i);
			return binaryAdd;
		}
		return "";
	}

	public String add(String str1, String str2) {
		if (str1 != null && str2 == null) {
			return str1;
		} else if (str1 == null && str2 != null) {
			return str2;
		} else if (str1 != null && str2 != null) {
			int d1 = Integer.parseInt(str1, 2);// Converting Binary to Decimal
			int d2 = Integer.parseInt(str2, 2);
			int i = (d1 + d2);
			String binaryAdd = Integer.toBinaryString(i);
			return binaryAdd;
		}
		return "";
	}

	public String subtract(String str1, String str2) {
		if (str1 != null && str2 == null) {
			return str1;
		} else if (str1 == null && str2 != null) {
			return str2;
		} else if (str1 != null && str2 != null) {
			int d1 = Integer.parseInt(str1, 2);// Converting Binary to Decimal
			int d2 = Integer.parseInt(str2, 2);
			int i = (d1 - d2);
			String binaryAdd = Integer.toBinaryString(i);
			return binaryAdd;
		}
		return "";
	}

}