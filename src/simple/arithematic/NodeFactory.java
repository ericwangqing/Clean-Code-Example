package simple.arithematic;

import simple.arithematic.node.AdditionNode;
import simple.arithematic.node.DivideNode;
import simple.arithematic.node.MinusNode;
import simple.arithematic.node.MultiplyNode;
import simple.arithematic.node.ValueNode;
import simple.arithematic.tree.Node;
import simple.arithematic.tree.ParsingException;

public class NodeFactory {

	public static Node createNode(String token) {
		if(token.equals("+")) return new AdditionNode();
		if(token.equals("-")) return new MinusNode();
		if(token.equals("*")) return new MultiplyNode();
		if(token.equals("/")) return new DivideNode();
		return new ValueNode(token);
	}
}
