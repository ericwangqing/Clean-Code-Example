package simple.arithematic.node;

import simple.arithematic.tree.Node;
import simple.arithematic.tree.ParsingException;
import simple.arithematic.tree.Tree;

public abstract class OperatorNode implements Node {
	protected Node leftChild;
	protected Node rightChild;

	@Override
	public abstract float evaluate();

	@Override
	public abstract int getPriority();

	public abstract String symbol();

	@Override
	public void insertIntoTree(Tree tree) {
		Node root = tree.getRoot();
		if (root == null)
			throw new ParsingException("The given expression isn't valid.");
		insertNodeAccordingToPriority(tree, root);
	}

	private void insertNodeAccordingToPriority(Tree tree, Node root) {
		if (this.getPriority() >= root.getPriority()) {
			insertNodeCalculatedAfterRoot(tree, root);
		}else {
			insertNodeCalculatedBefore(root);
		}
	}
	
	private void insertNodeCalculatedAfterRoot(Tree tree, Node root) {
		this.setLeftChild(root);
		tree.setRoot(this);
	}

	private void insertNodeCalculatedBefore(Node node) {
		Node parent = node;
		Node rightChild = node.getRightChild();
		if(this.getPriority() >= node.getRightChild().getPriority()) {
			insertNodeAsRightChild(node);
		}else {
			insertNodeCalculatedBefore(rightChild);
		}
	}

	private void insertNodeAsRightChild(Node node) {
		this.setLeftChild(node.getRightChild());
		node.setRightChild(this);
	}

	@Override
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	@Override
	public boolean hasRightChild() {
		return rightChild != null;
	}
	
	@Override
	public Node getLeftChild() {
		return leftChild;
	}

	@Override
	public Node getRightChild() {
		return rightChild;
	}

	@Override
	public void setLeftChild(Node node) {
		leftChild = node;
	}

	@Override
	public void setRightChild(Node node) {
		rightChild = node;
	}

	@Override
	public String toString() {
		return leftChild.toString() + " " + symbol() + " "
				+ rightChild.toString();
	}

	@Override
	public void showExpressionAndResult() {
		leftChild.showExpressionAndResult();
		rightChild.showExpressionAndResult();
		System.out.println(toString() + " = " + evaluate());
	}
}
