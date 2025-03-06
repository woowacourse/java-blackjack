package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public void addCards(final Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_SIZE
                && calculateDenominations() == BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return calculateDenominations() > BLACKJACK_VALUE;
    }

    public int calculateDenominations() {
        int sum = cards.stream()
                .map(Card::denomination)
                .map(Denomination::getValues)
                .map(List::getFirst)
                .reduce(0, Integer::sum);
        if (hasACE()) {
            sum = Denomination.changeAceValue(sum, BLACKJACK_VALUE);
        }

        return sum;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean isPossibleToAdd();

    private boolean hasACE() {
        return cards.stream()
                .map(Card::denomination)
                .anyMatch(denomination -> denomination == Denomination.ACE);
    }
}
