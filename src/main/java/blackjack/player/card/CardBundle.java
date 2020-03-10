package blackjack.player.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {
	private static final int ACE_WEIGHT = 10;
	private static final int BLACKJACK_MAXIMUM_VALUE = 21;
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public int compare(CardBundle cardBundle) {
		return Integer.compare(this.calculateScore(), cardBundle.calculateScore());
	}

	public int calculateScore() {
		int result = cards.stream()
			.mapToInt(Card::getScore)
			.sum();

		if (hasAce() && isBurst(result + ACE_WEIGHT)) {
			return result;
		}
		return result + ACE_WEIGHT;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	private boolean isBurst(int result) {
		return result > BLACKJACK_MAXIMUM_VALUE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CardBundle that = (CardBundle)o;
		return Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
