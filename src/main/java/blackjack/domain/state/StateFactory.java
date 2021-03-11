package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class StateFactory {
	public static PlayerState drawTwoCards(Card firstCard, Card secondCard) {
		Cards cards = new Cards();
		cards.addCard(firstCard);
		cards.addCard(secondCard);
		if (cards.calculateIncludeAce() == 21) {
			return new BlackJack(cards);
		}
		return new Hit(cards);
	}
}
