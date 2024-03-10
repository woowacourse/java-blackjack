package blackjack.domain.gamer;

public enum GameResult {

	WIN("승"),
	LOSE("패");

	private final String name;

	GameResult(String name) {
		this.name = name;
	}

	public boolean isWin() {
		return this == WIN;
	}

	public boolean isLose() {
		return this == LOSE;
	}

	public String getName() {
		return name;
	}
}
