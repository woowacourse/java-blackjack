package domain.card;

import domain.game.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int INITIAL_SIZE = 2;
    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int countAceCard() {
        return (int) this.cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public boolean isInitialSize() {
        return size() == INITIAL_SIZE;
    }

    public Score calculateScore() {
        return Score.from(this);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
