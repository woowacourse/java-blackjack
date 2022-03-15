package blackjack.domain.game;

import blackjack.domain.role.Role;
import java.util.HashMap;
import java.util.Map;

public class Betting {

	private final Map<Role, Money> bet;

	public Betting(Map<Role, Money> bettingMoney) {
		this.bet = new HashMap<>(bettingMoney);
	}
}
