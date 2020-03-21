package blackjack.player.domain.report;

import java.util.Objects;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.component.PlayerInfo;

public class GameReport {
	private final PlayerInfo playerInfo;
	private final GameResult gameResult;

	public GameReport(PlayerInfo playerInfo, GameResult gameResult) {
		Objects.requireNonNull(playerInfo, "플레이어 정보가 없습니다.");
		Objects.requireNonNull(gameResult, "결과 정보가 없습니다.");
		this.playerInfo = playerInfo;
		this.gameResult = gameResult;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public double getProfit() {
		return gameResult.getRate();
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
		return Objects.equals(playerInfo, that.playerInfo) &&
			gameResult == that.gameResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerInfo, gameResult);
	}
}
