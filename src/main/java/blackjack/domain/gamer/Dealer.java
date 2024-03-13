package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Dealer extends Gamer {
	public static final int INIT_CARD_COUNT = 2;
	public static final int MAX_HIT_SCORE = 16;

	private Dealer(CardHand cardHand) {
		super(cardHand);
	}

	public static Dealer newInstance() {
		return new Dealer(CardHand.createEmpty());
	}

	public static Dealer from(List<Card> cards) {
		return new Dealer(CardHand.from(cards));
	}

	public boolean hasHitScore() {
		return cardHand.isScoreLessOrEqual(MAX_HIT_SCORE);
	}

	public Card getInitCard() {
		return cardHand.getFirstCard();
	}

	@Override
	public String toString() {
		return "Dealer{" +
				"cardHand=" + cardHand +
				'}';
	}
}
