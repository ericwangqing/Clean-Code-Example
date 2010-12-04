package junit.framework;

public class ComparisonCompactor {
	private static final String ELLIPSIS = "...";
	private static final String DELTA_END = "]";
	private static final String DELTA_START = "[";
	private String expected;
	private String actual;
	private int contextLength;
	private int prefixLength;
	private int suffixLength;
	private String compactExpected;
	private String compactActual;

	public ComparisonCompactor(int contextLength, String expected, String actual) {
		this.contextLength = contextLength;
		this.expected = expected;
		this.actual = actual;
	}

	public String formatCompactComparison(String message) {
		if (shouldBeCompacted()) {
			compactExpectedAndActual();
			return Assert.format(message, compactExpected, compactActual);
		}else {
			return Assert.format(message, expected, actual);
		}
	}
	
	private boolean shouldBeCompacted() {
		return !shouldNotBeCompacted();
	}
	
	private boolean shouldNotBeCompacted() {
		return expected == null || actual == null || areStringsEqual();
	}

	private void compactExpectedAndActual() {
		findCommonPrefixAndSuffix();
		compactExpected = compact(expected);
		compactActual = compact(actual);
	}

	private void findCommonPrefixAndSuffix() {
		prefixLength = findCommonPrefix();
		int suffixLength = 0;
		for(; !suffixOverlapsPrefix(suffixLength); suffixLength++) {
			if(charFromEnd(expected, suffixLength) != 
				charFromEnd(actual, suffixLength))
				break;
		}
		this.suffixLength = suffixLength;
	}
	
	private int findCommonPrefix() {
		int prefixLength = 0;
		int end = Math.min(expected.length(), actual.length());
		for (; prefixLength < end; prefixLength++) {
			if (expected.charAt(prefixLength) != actual.charAt(prefixLength))
				break;
		}
		return prefixLength;
	}

	private boolean suffixOverlapsPrefix(int suffixLength) {
		return actual.length() - suffixLength <= prefixLength ||
		expected.length() - suffixLength <= prefixLength;
	}

	private Object charFromEnd(String s, int i) {
		return s.charAt(s.length()-i-1);
	}
	
	private String compact(String s) {
		return new StringBuffer()
		.append(startingEllipsis())
		.append(startingContext())
		.append(DELTA_START)
		.append(delta(s))
		.append(DELTA_END)
		.append(endingContext())
		.append(endingEllipsis())
		.toString();
	}

	private Object startingEllipsis() {
		return prefixLength > contextLength ? ELLIPSIS : "";
	}

	private String startingContext() {
		int contextStart = Math.max(0, prefixLength - contextLength);
		int contextEnd = prefixLength;
		return expected.substring(contextStart, contextEnd);
	}

	private Object delta(String s) {
		int deltaStart = prefixLength;
		int deltaEnd = s.length() - suffixLength;
		return s.substring(deltaStart, deltaEnd);
	}

	private Object endingContext() {
		int contextStart = expected.length() - suffixLength;
		int contextEnd = Math.max(contextStart + contextLength, expected.length());
		return expected.substring(contextStart, contextEnd);
	}

	private Object endingEllipsis() {
		return suffixLength > contextLength ? ELLIPSIS : "";
	}
}