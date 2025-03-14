package money;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import paticipant.Player;

public class WagerMoney {
	private final Map<Player, Money> money;

	public WagerMoney(final Map<Player, Money> money) {
		this.money = money;
	}

	public Map<Player, Money> getMoney() {
		return money;
	}

	public Map<Player, Money> calculateWagerResult() {
		final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
		return money.keySet().stream()
			.collect(Collectors.toMap(
				Function.identity(),
				player -> money.get(player).minus(wagerResultCalculator.calculate(player, money.get(player)))
			));
	}
}
