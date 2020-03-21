package blackjack.player.domain.report;

import java.util.Objects;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.component.Money;
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

	public Money calculateGamblerProfit() {
		return playerInfo.calculateResultMoney(gameResult.getRate());
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public double getProfit() {
		return gameResult.getRate();
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
