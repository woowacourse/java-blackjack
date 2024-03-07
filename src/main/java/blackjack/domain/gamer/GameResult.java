package blackjack.domain.gamer;

public enum GameResult {

	WIN("승"),
	LOSE("패");

	private final String name;

	GameResult(String name) {
		this.name = name;
	}
}
