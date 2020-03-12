package domain.user;

import domain.card.Card;

import java.util.List;

public class Dealer extends User {
	public Card openCard() {
		List<Card> dealerCards = cards.toList();
		return dealerCards.get(0);
	}
}
