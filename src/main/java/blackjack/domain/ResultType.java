package blackjack.domain;

public enum ResultType {
	WIN("승"),
	LOSE("패"),
	DRAW("무");

	private final String value;

	ResultType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
