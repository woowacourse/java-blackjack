package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards {

    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_SIZE && calculateDenominations() == BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return calculateDenominations() > BLACKJACK_VALUE;
    }

    public int calculateDenominations() {
        int sum = cards.stream()
                .map(Card::denomination)
                .map(Denomination::getMinValue)
                .reduce(0, Integer::sum);

        if (hasACE()) {
            return Denomination.convertAceValue(sum, BLACKJACK_VALUE);
        }

        return sum;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private boolean hasACE() {
        return cards.stream()
                .map(Card::denomination)
                .anyMatch(denomination -> denomination == Denomination.ACE);
    }
}
