package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitCards {

	public static final int INIT_SIZE = 2;
	
	private final List<Card> intiCards;

	public InitCards(Deck deck) {
		List<Card> cards = IntStream.range(0, INIT_SIZE).mapToObj(i -> deck.draw()).collect(Collectors.toList());
		this.intiCards = List.copyOf(cards);
	}

	public List<Card> getIntiCards() {
		return intiCards;
	}
}
