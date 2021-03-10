package blackjack.domain.game;

public enum WinnerFlag {
	WIN("승 "),
	DRAW("무 "),
	LOSE("패 ");

	private final String flagOutput;

	WinnerFlag(String flagOutput) {
		this.flagOutput = flagOutput;
	}

	public String getFlagOutput() {
		return flagOutput;
	}
}
