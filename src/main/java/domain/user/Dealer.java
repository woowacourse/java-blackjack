package domain.user;

import domain.card.Card;
import domain.user.strategy.draw.DealerDrawStrategy;

import java.util.List;

public class Dealer extends User {
	private static final String EMPTY_CARDS_EXCEPTION_MESSAGE = "카드가 한장도 없는데 오픈할 수 없습니다.";

	public Dealer() {
		super.drawStrategy = new DealerDrawStrategy();
	}

	public Card openCard() {
		List<Card> dealerCards = cards.toList();
		if (dealerCards.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_CARDS_EXCEPTION_MESSAGE);
		}
		if (dealerCards.get(0) == null) {
			throw new NullPointerException(NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		}
		return dealerCards.get(0);
	}
}
