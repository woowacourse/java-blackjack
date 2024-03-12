package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	static final int INIT_SIZE = 52;
	private static final String SIZE_ERROR_MESSAGE = String.format("덱의 사이즈가 %d장이 아닙니다.", INIT_SIZE);
	private static final String DUPLICATION_ERROR_MESSAGE = "카드는 중복될 수 없습니다.";

	private final List<Card> cards;

	public Deck(List<Card> cards) {
		List<Card> copyDeck = new ArrayList<>(cards);
		validate(copyDeck);
		this.cards = copyDeck;
	}

	public static Deck createUnShuffled() {
		return new Deck(createCards());
	}

	private static List<Card> createCards() {
		List<Card> cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			cards.addAll(createCardsOfSuit(suit));
		}

		return cards;
	}

	private static List<Card> createCardsOfSuit(Suit suit) {
		List<Card> cards = new ArrayList<>();
		for (Rank rank : Rank.values()) {
			cards.add(new Card(suit, rank));
		}

		return cards;
	}

	private void validate(List<Card> cards) {
		validateDuplicate(cards);
		validateSize(cards);
	}

	private void validateDuplicate(List<Card> cards) {
		long distinctCount = cards.stream()
			.distinct()
			.count();

		if (cards.size() != distinctCount) {
			throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
		}
	}

	private void validateSize(List<Card> deck) {
		if (deck.size() != INIT_SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}
	}

	public List<Card> drawCards(int count) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			cards.add(drawCard());
		}

		return cards;
	}

	public Card drawCard() {
		return cards.remove(0);
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public int size() {
		return cards.size();
	}

	@Override
	public String toString() {
		return "Deck{" +
				"cards=" + cards +
				'}';
	}
}
