package simple.arithematic.node;

import simple.arithematic.tree.Node;
import simple.arithematic.tree.ParsingException;
import simple.arithematic.tree.Tree;
import sun.awt.SunToolkit.InfiniteLoop;

public class ValueNode implements Node {
	
	protected int value;

	public ValueNode(String token) {
		this.value = Integer.parseInt(token);
	}

	@Override
	public float evaluate() {
		return value;
	}

	@Override
	public void insertIntoTree(Tree tree) {
		Node root = tree.getRoot();
		if(root == null) {
			tree.setRoot(this);
		}else {
			insertToEndestRightOffspring(root);
		}
	}
	
	private void insertToEndestRightOffspring(Node node) {
		if(node.hasRightChild()) {
			insertToEndestRightOffspring(node.getRightChild());
		}else {
			node.setRightChild(this);
		}
	}
	
	@Override
	public boolean hasLeftChild() {
		return false;
	}

	@Override
	public boolean hasRightChild() {
		return false;
	}

	
	@Override
	public Node getLeftChild() {
		return null;
	}

	@Override
	public Node getRightChild() {
		return null;
	}

	@Override
	public void setLeftChild(Node node) {
		throw new ParsingException("Can't set left child on a value node.");
	}

	@Override
	public void setRightChild(Node node) {
		throw new ParsingException("Can't set right child on a value node.");
	}
	
	@Override
	public void showExpressionAndResult() {
		System.out.println("value: " + evaluate());
	}
	
	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public int getPriority() {
		return 0;
	}
}
