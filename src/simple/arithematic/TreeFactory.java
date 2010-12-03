package simple.arithematic;

import java.util.ArrayList;

import simple.arithematic.tree.Node;
import simple.arithematic.tree.ParsingException;
import simple.arithematic.tree.Tree;

public class TreeFactory {
	

	private char[] characters;
	private StringBuffer tempToken = new StringBuffer();
	private ArrayList<String> tokens = new ArrayList<String>();
	
	private Tree tree = new Tree();
	private Node currentNode;
	
	private static final char[] SPACE_CHARACTERS = {' ', '\t'};
	private static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	public static Tree parseTree(String expression)
			throws ParsingException {
		TreeFactory factory = new TreeFactory();
		factory.createTree(expression);
		return factory.tree;
	}

	private void createTree(String expression) {
		characters = expression.toCharArray();
		for (char character : characters)
			makeNodeOnTree(character);
		makeLastTokenAsNodeOnTree(tempToken.toString());
	}

	private void makeNodeOnTree(char character) {
		if(isSpace(character)) return;
		
		if(isDigit(character)) {
			tempToken.append(character);
		}else {
			createNodeOnTree("" + character);
		}
	}

	private boolean isSpace(char character) {
		for (char space : SPACE_CHARACTERS) {
			if(character == space) return true;
		}
		return false;
	}

	private boolean isDigit(char character) {
		for (char digit : DIGIT_CHARACTERS) {
			if(character == digit) return true;
		}
		return false;
	}

	private void createNodeOnTree(String token) {
		if(tempToken.length() > 0) {
			makeLastTokenAsNodeOnTree(tempToken.toString());
			tempToken = new StringBuffer();
		}
		makeLastTokenAsNodeOnTree(token);
	}

	private void makeLastTokenAsNodeOnTree(String token) {
		try {
			currentNode = NodeFactory.createNode(token);
			currentNode.insertIntoTree(tree);
		} catch (NumberFormatException e) {
			throw new ParsingException(e);
		}
	}
}
