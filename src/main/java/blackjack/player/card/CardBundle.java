package blackjack.player.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {
	private static final int ACE_WEIGHT = 10;
	private static final int BLACKJACK_VALUE = 21;
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public int compare(CardBundle cardBundle) {
		if (cardBundle.isBurst()) {
			return -1;
		}
		return Integer.compare(this.calculateScore(), cardBundle.calculateScore());
	}

	public int calculateScore() {
		int resultScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();

		if (hasAce() && isBurst(resultScore + ACE_WEIGHT)) {
			return resultScore;
		}
		if (hasAce()) {
			return resultScore + ACE_WEIGHT;
		}
		return resultScore;
	}

	public boolean isBlackjack() {
		return cards.size() == 2 && calculateScore() == BLACKJACK_VALUE;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	public boolean isBurst() {
		return calculateScore() > BLACKJACK_VALUE;
	}

	private boolean isBurst(int result) {
		return result > BLACKJACK_VALUE;
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
