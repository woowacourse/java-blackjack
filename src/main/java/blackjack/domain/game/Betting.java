package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

public class Betting {

	private final Map<String, Money> betting;

	public Betting(final Map<String, Money> bettingMoney) {
		this.betting = new HashMap<>(bettingMoney);
	}

	public Money getBettingByName(String name) {
		return betting.get(name);
	}
}
