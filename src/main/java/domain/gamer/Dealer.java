package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;

public class Dealer extends Gamer {
	public static final int INIT_CARD_COUNT = 2;
	public static final int MAX_HIT_SCORE = 16;

	private final Deck deck;

	private Dealer(Deck deck, CardHand cardHand) {
		super(cardHand);
		this.deck = deck;
		this.deck.shuffle();
	}

	public static Dealer newInstance(Deck deck) {
		return new Dealer(deck, CardHand.createEmpty());
	}

	public static Dealer of(Deck deck, List<Card> cards) {
		return new Dealer(deck, CardHand.from(cards));
	}

	public List<Card> dealInit() {
		return deck.drawCards(INIT_CARD_COUNT);
	}

	public Card dealCard() {
		return deck.drawCard();
	}

	public int deckSize() {
		return deck.size();
	}

	private boolean hasHitScore() {
		return cardHand.isScoreLessOrEqual(MAX_HIT_SCORE);
	}

	public boolean tryHit() {
		if (hasHitScore()) {
			cardHand.add(dealCard());
			return true;
		}
		return false;
	}
}
