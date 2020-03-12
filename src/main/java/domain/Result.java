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

	public static boolean isLose(Result result) {
		return result == LOSE;
	}

	public static boolean isDraw(Result result) {
		return result == DRAW;
	}

	public static boolean isWin(Result result) {
		return result == WIN;
	}
}
