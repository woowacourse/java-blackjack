package money;

import java.util.LinkedHashMap;
import java.util.Map;

import paticipant.Player;

public class WagerMoney {
	private final Map<Player, Money> money;

	public WagerMoney(final Map<Player, Money> money) {
		this.money = new LinkedHashMap<>(money);
	}

	public Map<Player, Money> getMoney() {
		return money;
	}

	public Map<Player, Money> calculateWagerResult() {
		final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
		final Map<Player, Money> wagerResult = new LinkedHashMap<>();
		for (final Player player : money.keySet()) {
			final Money playerWager = money.get(player);
			final Money playerWagerResult = wagerResultCalculator.calculate(player, playerWager);
			wagerResult.put(player, playerWagerResult.minus(playerWager));
		}
		return wagerResult;
	}
}
