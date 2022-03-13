package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
	private static final int SELECT_ACE_VALUE_STANDARD = 11;
	private static final int ADD_BENEFICIAL_VALUE = 10;

	private final List<Card> cards;

	public Cards() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int sum() {
        return getCalibratedSum(cards.stream()
            .mapToInt(Card::getValue)
            .sum());
	}

    private int getCalibratedSum(int sum) {
        if (hasAce()) {
            return selectAceValue(sum);
        }
        return sum;
    }

    private boolean hasAce() {
		return cards.stream()
            .anyMatch(Card::isAce);
	}

	private int selectAceValue(int sum) {
		if (sum <= SELECT_ACE_VALUE_STANDARD) {
			sum += ADD_BENEFICIAL_VALUE;
		}
		return sum;
	}

	public List<Card> getCards() {
		return cards;
	}
}
