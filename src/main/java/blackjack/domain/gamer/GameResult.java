package blackjack.domain.gamer;

public enum GameResult {

	BLACKJACK_WIN("블랙잭승"),
	WIN("승"),
	DRAW("무승부"),
	LOSE("패");

	private final String name;

	GameResult(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
