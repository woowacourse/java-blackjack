package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Gamer {
	public static final int INIT_CARD_COUNT = 2;
	public static final int MAX_HIT_SCORE = 16;

	public Dealer() {
		super(Cards.createEmpty());
	}

	private Dealer(final Cards cards) {
		super(cards);
	}

	public static Dealer of(final List<Card> cards) {
		return new Dealer(Cards.from(cards));
	}

	public boolean hasHitScore() {
		return cards.isScoreLessOrEqual(MAX_HIT_SCORE);
	}

	public Card getFirstCard() {
		return cards.indexOf(0);
	}
}
