package blackjack.player.domain;

import blackjack.card.domain.CardBundle;

public class GamblerHelper {
	public static Gambler aGambler(String name, int money) {
		return new Gambler(new CardBundle(), name, Money.create(money));
	}
}
