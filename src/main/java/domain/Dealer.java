package domain;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {

	public Dealer(Cards cards) {
		super("딜러", cards);
	}

	public Card getOneDealerCard() {
		return cards.getCards().get(0);
	}
}
