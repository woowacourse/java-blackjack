package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;

public class Hand {
    private static final int BLACK_JACK = 21;
    private static final int GAP_BETWEEN_ACE_VALUE = 10;
    private static final String DELIMITER = ", ";

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

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
    }
}
