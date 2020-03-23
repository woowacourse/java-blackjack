package blackjack.player.domain.report;

import java.util.Objects;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Money;

public class GameReport {
	private final Gambler gambler;
	private final GameResult gameResult;

	public GameReport(Gambler gambler, GameResult gameResult) {
		Objects.requireNonNull(gambler, "플레이어 정보가 없습니다.");
		Objects.requireNonNull(gameResult, "결과 정보가 없습니다.");
		this.gambler = gambler;
		this.gameResult = gameResult;
	}

	public Money calculateGamblerProfit() {
		return gambler.calculateProfit(gameResult.getRate());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GameReport that = (GameReport)o;
		return Objects.equals(gambler, that.gambler) &&
			gameResult == that.gameResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gambler, gameResult);
	}

	public Gambler getGambler() {
		return gambler;
	}
}
