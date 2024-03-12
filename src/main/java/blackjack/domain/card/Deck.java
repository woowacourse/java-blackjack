package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

	private final List<Card> cards;

	public Deck() {
		this.cards = initAllCards();
	}

	private List<Card> initAllCards() {
		return Arrays.stream(CardShape.values())
			.flatMap(cardShape -> Arrays.stream(CardNumber.values())
				.map(number -> new Card(cardShape, number)))
			.collect(Collectors.toCollection(ArrayList::new));
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card draw() {
		if (cards.isEmpty()) {
			cards.addAll(initAllCards());
			shuffle();
		}

		return cards.remove(0);
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
