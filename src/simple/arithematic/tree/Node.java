package simple.arithematic.tree;

public interface Node {
	public static final int PRIORITY_1_LEVEL = 1;
	public static final int PRIORITY_2_LEVEL = 2; // for *, /, ...
	public static final int PRIORITY_3_LEVEL = 3; // for +, -, ...
	
	public void insertIntoTree(Tree tree);
	public float evaluate();
	public Node getLeftChild();
	public Node getRightChild();
	public int getPriority();
	public void setLeftChild(Node node);
	public void setRightChild(Node node);
	public boolean hasLeftChild();
	public boolean hasRightChild();
	public void showExpressionAndResult();
}
