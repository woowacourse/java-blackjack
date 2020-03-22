package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int TEN = 10;

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculatePoint() {
        if (hasAce() && sumPoint() + TEN <= BLACK_JACK) {
            return sumPoint() + TEN;
        }
        return sumPoint();
    }

    private int sumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public boolean areBust() {
        return calculatePoint() > BLACK_JACK;
    }

    public boolean areBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && areBlackJackPoint();
    }

    public boolean areBlackJackPoint() {
        return calculatePoint() == BLACK_JACK;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
