package domain;

public enum Type {
	HEART("♥"),
	SPADE("♠"),
	CLUBS("♣"),
	DIAMOND("◆");

	private String type;

	Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
