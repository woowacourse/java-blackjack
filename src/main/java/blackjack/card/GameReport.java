package blackjack.card;

import java.util.Objects;

public class GameReport {
	private final String name;
	private final GameResult gameResult;

	public GameReport(String name, GameResult gameResult) {
		this.name = name;
		this.gameResult = gameResult;
	}

	public String getName() {
		return name;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public String getMessage() {
		return gameResult.getMessage();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GameReport that = (GameReport)o;
		return Objects.equals(name, that.name) &&
			gameResult == that.gameResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, gameResult);
	}
}
