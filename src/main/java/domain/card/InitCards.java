package domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitCards {

	private static final int INIT_SIZE = 2;

	private final List<Card> intiCards;

	public InitCards(Deck deck) {
		List<Card> cards = generateInitCards(deck);
		this.intiCards = List.copyOf(cards);
	}

	private List<Card> generateInitCards(Deck deck) {
		List<Card> cards = IntStream.range(0, INIT_SIZE)
			.mapToObj(i -> deck.draw())
			.collect(Collectors.toList());
		return cards;
	}

	public List<Card> getInitCards() {
		return List.copyOf(intiCards);
	}
}
