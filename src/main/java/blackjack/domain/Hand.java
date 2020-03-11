package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACK_JACK = 21;
    public static final int GAP_BETWEEN_ACE_VALUE = 10;

    private List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public boolean isBusted() {
        return calculate() > BLACK_JACK;
    }

    public int calculate() {
        int total = cards.stream()
            .map(Card::getRankValue)
            .reduce(0, Integer::sum);
        return reduceIfAceExists(total);
    }

    private int reduceIfAceExists(int total) {
        int aceCount = countAce();
        while (total > BLACK_JACK && aceCount > 0) {
            total -= GAP_BETWEEN_ACE_VALUE;
            aceCount--;
        }
        return total;
    }

    private int countAce() {
        return Math.toIntExact(
            cards.stream()
                .filter(Card::isAce)
                .count()
        );
    }
}
