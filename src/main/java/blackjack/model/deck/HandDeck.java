package blackjack.model.deck;

import blackjack.model.GameRule;
import blackjack.model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandDeck {

    private static final int ACE_VALUE_ADJUSTING = 10;

    private final List<Card> cards = new ArrayList<>();

    public HandDeck() {
    }

    public void addCard(Card card) {
        validateDuplicatedCard(card);
        cards.add(card);
    }

    public int calculateTotalScore() {
        int sum = cards.stream()
                .mapToInt(Card::getCardScore)
                .sum();

        if (hasAceCard()) {
            sum = adjustAceValueIfNotBusted(sum + ACE_VALUE_ADJUSTING);
        }
        return sum;
    }

    private int adjustAceValueIfNotBusted(int adjustedSum) {
        if (!GameRule.isBusted(adjustedSum)) {
            return adjustedSum;
        }
        return adjustedSum - ACE_VALUE_ADJUSTING;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private void validateDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("[ERROR] 중복된 카드는 받을 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
