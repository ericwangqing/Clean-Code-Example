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
		compactExpected = compactString(expected);
		compactActual = compactString(actual);
	}

	private void findCommonPrefixAndSuffix() {
		prefixLength = findCommonPrefix();
		int suffixLength = 0;
		for(; !suffixOverlapsPrefix(suffixLength); suffixLength++) {
			if(charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength))
				break;
		}
		this.suffixLength = suffixLength;
	}

	private boolean suffixOverlapsPrefix(int suffixLength) {
		return actual.length() - suffixLength <= prefixLength ||
		expected.length() - suffixLength <= prefixLength;
	}

	private Object charFromEnd(String s, int i) {
		return s.charAt(s.length()-i-1);
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
	
	private String compactString(String source) {
		String result = DELTA_START
		+ source.substring(prefixLength, source.length() - suffixLength + 1)
		+ DELTA_END;
		if (prefixLength > 0)
			result = computeCommonPrefix() + result;
		if (suffixLength > 0)
			result = result + computeCommonSuffix();
		return result;
	}

	private String computeCommonPrefix() {
		return (prefixLength > contextLength ? ELLIPSIS : "")
				+ expected.substring(Math.max(0, prefixLength - contextLength),
						prefixLength);
	}

	private String computeCommonSuffix() {
		int end = Math.min(expected.length() - suffixLength + 1 + contextLength,
				expected.length());
		return expected.substring(expected.length() - suffixLength + 1, end)
				+ (expected.length() - suffixLength + 1 < expected.length()
						- contextLength ? ELLIPSIS : "");
	}

	private boolean areStringsEqual() {
		return expected.equals(actual);
	}
}