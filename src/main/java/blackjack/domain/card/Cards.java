package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final static int BLACK_JACK = 21;
    private final static int TEN = 10;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards(final Card... cards) {
        this(Arrays.asList(cards));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackJack() {
        return calculateScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK;
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::numberScore)
                .sum();
    }

    public int calculateScore() {
        int sum = sum();
        int aceCount = getAceCount();
        while (aceCount-- > 0 && sum > BLACK_JACK) {
         sum -= TEN;
        }
        return sum;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }
}
