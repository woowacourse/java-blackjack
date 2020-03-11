package domain;

public enum Result {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private final String name;

	Result(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
