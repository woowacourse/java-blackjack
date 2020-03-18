package domain.user;

import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {
	private static final int MAXIMUM_DRAWABLE_SCORE = 16;
	private static final int FIRST_SHOW_SIZE = 1;

	public Dealer() {
		super(Name.ofDealer());
	}

	private Dealer(Name name, Cards cards) {
		super(name, cards);
	}

	public static Dealer fromCards(Card... cards) {
		return new Dealer(Name.ofDealer(), new Cards(Arrays.asList(Objects.requireNonNull(cards))));
	}

	@Override
	public boolean isDrawable() {
		return cards.calculateScore() <= MAXIMUM_DRAWABLE_SCORE;
	}

	@Override
	public int getFirstShowCardSize() {
		return FIRST_SHOW_SIZE;
	}
}
