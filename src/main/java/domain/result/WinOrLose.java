package domain.result;

public enum WinOrLose {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private final String result;

	WinOrLose(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
