package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK = 21;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isOverBlackJack() {
        return sum() > BLACK_JACK;
    }

    public int sum() {
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
