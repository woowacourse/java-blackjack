package domain;

public enum Type {
	HEART("HEART"),
	SPADE("SPADE"),
	DIAMOND("DIAMOND"),
	CLOVER("CLOVER");

	private final String value;

	Type(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
