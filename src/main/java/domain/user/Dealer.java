package domain.user;

import java.util.List;

import domain.card.Card;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;

	private String name;

	public Dealer() {
		this.name = "딜러";
	}

	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards().subList(0, 1);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
