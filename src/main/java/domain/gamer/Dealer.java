package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.Deck;

public class Dealer extends Gamer {
	public static final int INIT_CARD_COUNT = 2;
	public static final int MAX_HIT_SCORE = 16;

	private final Deck deck;

	public Dealer(Deck deck, List<Card> cardHand) {
		// TODO: 빈 리스트를 초기에 가지도록 하는 정팩메 만들기
		super(cardHand);
		this.deck = deck;
		this.deck.shuffle();
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
