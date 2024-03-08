package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	static final int INIT_SIZE = 52;

	private final List<Card> cards;

	public Deck(List<Card> cards) {
		ArrayList<Card> copyDeck = new ArrayList<>(cards);
		validate(copyDeck);
		this.cards = copyDeck;
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
			throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
		}
	}

	private void validateSize(List<Card> deck) {
		if (deck.size() != INIT_SIZE) {
			throw new IllegalArgumentException(String.format("덱의 사이즈가 %d장이 아닙니다.", INIT_SIZE));
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
}
