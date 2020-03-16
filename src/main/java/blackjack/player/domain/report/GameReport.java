package blackjack.player.domain.report;

import java.util.Objects;

import blackjack.card.domain.GameResult;

public class GameReport {
	private final String name;
	private final GameResult gameResult;

	public GameReport(String name, GameResult gameResult) {
		validate(name, gameResult);
		this.name = name;
		this.gameResult = gameResult;
	}

	private void validate(String name, GameResult gameResult) {
		checkName(name);
		checkGameResult(gameResult);
	}

	private void checkGameResult(GameResult gameResult) {
		if (gameResult == null) {
			throw new IllegalArgumentException("GameResult가 존재하지 유효하지 않습니다.");
		}
	}

	private void checkName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("비어있는 이름을 사용했습니다.");
		}
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return gameResult.getMessage();
	}

	public boolean isWin() {
		return this.gameResult == GameResult.WIN;
	}

	public boolean isDraw() {
		return this.gameResult == GameResult.DRAW;
	}

	public boolean isLose() {
		return this.gameResult == GameResult.LOSE;
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
