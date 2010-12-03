package simple.arithematic;

import java.util.Scanner;

import simple.arithematic.tree.Tree;

public class Main {

	private static String expression;
	private static Tree tree;

	public static void main(String[] args) {
		getInputExpression();
		parseExpressionToTree();
		showComputeProcessAndResult();
	}

	private static void getInputExpression() {
		System.out.println("Please input your arithemetic expression:");
		Scanner scanner = new Scanner(System.in);
		expression = scanner.nextLine();
	}

	private static void parseExpressionToTree() {
		tree = TreeFactory.parseTree(expression);
	}
	
	private static void showComputeProcessAndResult() {
		System.out.println("The detailed calculation process is: ");
		tree.calculateAndShowDetailsOfEachStep();
	}
}
