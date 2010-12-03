package simple.arithematic.tree;

public class ParsingException extends RuntimeException {
	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 5289650044334395570L;
}
