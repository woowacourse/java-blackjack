package domain.result;

import java.util.Map;

import domain.gamer.Money;
import domain.gamer.Player;
import exception.MissResultException;

public class GameResult {
	private Map<Player, Money> playersTotalEarning;
	private Money dealerEarning;

	public GameResult(Map<Player, Money> gameResult) {
		this.playersTotalEarning = gameResult;
		this.dealerEarning = playersTotalEarning.values()
			.stream()
			.map(Money::reversion)
			.reduce(Money::sum)
			.orElseThrow(MissResultException::new);
	}

	public Map<Player, Money> getPlayersTotalEarning() {
		return playersTotalEarning;
	}

	public Money getDealerEarning() {
		return dealerEarning;
	}
}
