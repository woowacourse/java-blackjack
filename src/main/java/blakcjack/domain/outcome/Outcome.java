package blakcjack.domain.outcome;

public enum Outcome {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private static final int MAXIMUM_INDEX = 2;

	final String korean;

	Outcome(final String korean) {
		this.korean = korean;
	}

	public Outcome getDealerOutcome() {
		return values()[MAXIMUM_INDEX - ordinal()];
	}

	public String toKorean() {
		return korean;
	}
}
