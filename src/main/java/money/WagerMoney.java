package money;

import java.util.Map;

import paticipant.Player;

public class WagerMoney {
	private final Map<Player, Money> money;

	public WagerMoney(final Map<Player, Money> money) {
		this.money = money;
	}

	public Map<Player, Money> getMoney() {
		return money;
	}
}
