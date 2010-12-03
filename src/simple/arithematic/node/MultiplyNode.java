package simple.arithematic.node;

public class MultiplyNode extends OperatorNode {

	@Override
	public float evaluate() {
		return leftChild.evaluate() * rightChild.evaluate();
	}

	@Override
	public String symbol() {
		return "*";
	}

	@Override
	public int getPriority() {
		return PRIORITY_2_LEVEL;
	}

}
