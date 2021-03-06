package blakcjack.domain.outcome;

public enum Outcome {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private static final int MAXIMUM_INDEX = 2;

	private final String message;

	Outcome(final String message) {
		this.message = message;
	}

	public Outcome getDealerOutcome() {
		return values()[MAXIMUM_INDEX - ordinal()];
	}

	public String getMessage() {
		return message;
	}
}
