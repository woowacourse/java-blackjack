package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;
import domain.score.Score;

public class Dealer extends User {
	private static final int MINIMUM_NOT_DRAWABLE_SCORE = 17;
	private static final int FIRST_SHOW_SIZE = 1;

	public Dealer() {
		this(Name.ofDealer(), new Cards(new ArrayList<>()));
	}

	private Dealer(Name name, Cards cards) {
		super(name, cards);
	}

	public static Dealer fromCards(Card... cards) {
		return new Dealer(Name.ofDealer(), new Cards(Arrays.asList(Objects.requireNonNull(cards))));
	}

	@Override
	public boolean isDrawable() {
		return calculateScore().isSmallerThan(Score.ofValue(MINIMUM_NOT_DRAWABLE_SCORE));
	}

	@Override
	public int getFirstShowCardSize() {
		return FIRST_SHOW_SIZE;
	}
}
