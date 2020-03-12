package domain.user;

import domain.card.Card;
import domain.user.strategy.draw.DealerDrawStrategy;

import java.util.List;

public class Dealer extends User {

	public Dealer() {
		super.drawStrategy = new DealerDrawStrategy();
	}

	public Card openCard() {
		List<Card> dealerCards = cards.toList();
		return dealerCards.get(0);
	}
}
