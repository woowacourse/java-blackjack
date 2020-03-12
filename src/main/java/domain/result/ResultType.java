package domain.result;

public enum ResultType {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private final String name;

	ResultType(String name) {
		this.name = name;
	}

	public static ResultType opposite(ResultType resultType) {
		if (WIN.equals(resultType)) {
			return LOSE;
		}
		if (LOSE.equals(resultType)) {
			return WIN;
		}
		return DRAW;
	}

	public String getName() {
		return name;
	}
}
