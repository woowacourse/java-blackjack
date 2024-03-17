package blackjack.domain.bet;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.gamer.Player;

public class Betting {
	private static final int MONEY_MAX = 100000000;
	private static final int MONEY_MIN = 1000;

	private final Map<Player, Integer> playersBetting;

	public Betting() {
		this(new HashMap<>());
	}

	public Betting(Map<Player, Integer> playersBetting) {
		this.playersBetting = playersBetting;
	}

	public void add(final Player player, final int money) {
		if (money < MONEY_MIN || money > MONEY_MAX) {
			throw new IllegalArgumentException("배팅 금액이 1000원 이상 100000000원 이하여야 합니다.");
		}
		playersBetting.put(player, money);
	}
}
