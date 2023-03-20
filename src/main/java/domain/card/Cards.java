package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

	private static final int BLACK_JACK_SCORE = 21;
	private static final int ACE_OFFSET = 10;

	private final List<Card> cards;

	public Cards() {
		this.cards = new ArrayList<>();
	}

	public void addCard(final Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}

	public int calculateScore() {
		int sum = cards.stream()
			.map(Card::getScore)
			.reduce(0, Integer::sum);
		if (isContainAce() && sum + ACE_OFFSET <= BLACK_JACK_SCORE) {
			return sum + Cards.ACE_OFFSET;
		}

		return sum;
	}

	private boolean isContainAce() {
		return cards.stream()
			.map(Card::getPoint)
			.anyMatch(name -> name.equals(Denomination.ACE.getPoint()));
	}

	public boolean isHittable(int score) {
		return calculateScore() < score;
	}

	public List<String> getCardNames() {
		List<String> cardNames = new ArrayList<>();
		for (Card card : cards) {
			cardNames.add(card.getCardName());
		}
		return cardNames;
	}
}
