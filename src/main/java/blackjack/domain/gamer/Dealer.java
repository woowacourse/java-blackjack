package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Deck;

public class Dealer extends Gamer {
	public static final int INIT_CARD_COUNT = 2;
	public static final int MAX_HIT_SCORE = 16;

	private final Deck deck;

	private Dealer(final Deck deck, final CardHand cardHand) {
		super(cardHand);
		this.deck = deck;
	}

	public static Dealer newInstance(final Deck deck) {
		return new Dealer(deck, CardHand.createEmpty());
	}

	public static Dealer of(final Deck deck, final List<Card> cards) {
		return new Dealer(deck, CardHand.from(cards));
	}

	public List<Card> dealInitCards() {
		return deck.drawInitCards();
	}

	public Card dealCard() {
		return deck.drawCard();
	}

	public void drawCard() {
		cardHand.add(deck.drawCard());
	}

	public int deckSize() {
		return deck.size();
	}

	public boolean hasHitScore() {
		return cardHand.isScoreLessOrEqual(MAX_HIT_SCORE);
	}
}
