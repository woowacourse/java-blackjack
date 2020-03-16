package blackjack.domain.participants;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    public static final int BLACK_JACK = 21;
    public static final int GAP_BETWEEN_ACE_VALUE = 10;
    public static final String DELIMITER = ", ";

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int countAddedCard() {
        return cards.size() - Participants.FIRST_CARDS_COUNT;
    }

    public boolean isBusted() {
        return calculate() > BLACK_JACK;
    }

    public int calculate() {
        int total = cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();

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

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
    }
}
