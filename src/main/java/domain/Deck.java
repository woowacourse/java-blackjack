package domain;

import java.util.Collections;
import java.util.List;

/**
 *    카드 덱을 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Deck {
	private static final int FIRST_CARD = 0;

	private List<Card> deck;

	public Deck() {
		this.deck = CardFactory.create();
		Collections.shuffle(deck);
	}

	public Card deal() {
		return deck.remove(FIRST_CARD);
	}
}
