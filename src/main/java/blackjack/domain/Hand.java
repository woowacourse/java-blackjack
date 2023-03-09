package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACK_JACK = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    private final List<Card> cards = new ArrayList<>();
    private int sum = 0;

    public void add(Card card) {
        sum += card.getNumberValue();
        cards.add(card);
    }

    public boolean isBlackJack() {
        return sum == BLACK_JACK;
    }

    public boolean isBust() {
        return sum > BLACK_JACK;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    public int getSum() {
        if (isBust() && hasACE()) {
            return sum - ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
