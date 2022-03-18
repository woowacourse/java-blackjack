package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class InitialTurn {
	public static State createState(Cards cards) {
		if(cards.isBlackJack()) {
			return new BlackJack(cards);
		}
		return new Hit(cards);
	}
}
