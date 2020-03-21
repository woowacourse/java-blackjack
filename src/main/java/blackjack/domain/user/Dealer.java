package blackjack.domain.user;

import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.exceptions.InvalidDealerException;

public class Dealer extends User {
	public static final String NAME = "Dealer";
	public static final int DEALER_DRAWABLE_MAX_SCORE = 16;

	Dealer(String name, Hand hand) {
		super(name, hand);
	}

	public Dealer(String name) {
		super(name);
	}

	public static Dealer from(List<Card> cards) {
		validate(cards);
		Hand hand = new Hand();
		hand.add(cards);

		return new Dealer(NAME, hand);
	}

	private static void validate(List<Card> cards) {
		if (Objects.isNull(cards) || cards.isEmpty()) {
			throw new InvalidDealerException(InvalidDealerException.EMPTY);
		}
	}

	public boolean isBlackjack() {
		return calculateResultScore().isBlackjack();
	}

	@Override
	public boolean canDraw() {
		return calculateResultScore().isLowerThan(DEALER_DRAWABLE_MAX_SCORE);
	}

	@Override
	public List<Card> getInitialDealtHand() {
		return getHand().subList(0, 1);
	}
}
