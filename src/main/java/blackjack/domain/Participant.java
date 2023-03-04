package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private static final int BLACK_JACK = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    private final List<Card> cards;

    public Participant() {
        cards = new ArrayList<>();
    }


    public void take(Card card) {
        cards.add(card);
    }

    public int computeSumOfCards() {
        int sum = cards.stream()
                .map(Card::getNumberValue)
                .reduce(0, Integer::sum);

        if ((sum > BLACK_JACK) && hasACE()) {
            return (sum - ACE_ADDITIONAL_VALUE);
        }

        return sum;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    public boolean isBust() {
        return computeSumOfCards() > BLACK_JACK;
    }

    public boolean isAvailable() {
        return !(isBlackJack()) && !(isBust());
    }

    private boolean isBlackJack() {
        return computeSumOfCards() == BLACK_JACK;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
