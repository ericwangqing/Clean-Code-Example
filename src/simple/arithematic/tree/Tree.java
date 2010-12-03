package simple.arithematic.tree;

import simple.arithematic.node.ValueNode;

public class Tree {
	private Node root;

	public void calculateAndShowDetailsOfEachStep() {
		root.showExpressionAndResult();
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node node) {
		root = node;
	}

}
