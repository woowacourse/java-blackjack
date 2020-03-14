package domain.user;

import java.util.Arrays;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;
	private static final int FIRST_SHOW_SIZE = 1;
	private static final String DEALER_DEFAULT_NAME = "딜러";

	public Dealer() {
		super(new Name(DEALER_DEFAULT_NAME));
	}

	private Dealer(Name name, Cards cards) {
		super(name, cards);
	}

	public static Dealer fromCards(Card... cards) {
		return new Dealer(new Name(DEALER_DEFAULT_NAME), new Cards(Arrays.asList(cards)));
	}

	@Override
	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}

	@Override
	protected int getFirstShowCardSize() {
		return FIRST_SHOW_SIZE;
	}
}
