package domain.user;

import java.util.List;

import domain.card.Card;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;
	private static final int INITIAL_FROM_INDEX = 0;
	private static final int INITIAL_TO_INDEX = 1;
	private static final String DEALER_DEFAULT_NAME = "딜러";

	private final String name;

	public Dealer() {
		this.name = DEALER_DEFAULT_NAME;
	}

	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards().subList(INITIAL_FROM_INDEX, INITIAL_TO_INDEX);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
