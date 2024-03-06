package domain;

import java.util.Collections;
import java.util.List;

public class Dealer {
	private final List<Card> deck;
	private final List<Card> cardHand;

	// TODO: 빈 리스트를 초기에 가지도록 하는 정팩메 만들기
	public Dealer(List<Card> deck, List<Card> cardHand) {
		Collections.shuffle(deck);
		this.deck = deck;
		this.cardHand = cardHand;
	}

	public List<Card> dealInit() {
		return List.of(deck.remove(0), deck.remove(0));
	}

	public void receiveInitCards(List<Card> cards) {
		cardHand.addAll(cards);
	}

	public int deckSize() {
		return deck.size();
	}

	public List<Card> getCardHand() {
		return cardHand;
	}
}
