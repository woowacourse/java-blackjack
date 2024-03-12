package domain.card;

import domain.game.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int countAceCard() {
        return (int) this.cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public Score calculateScore() {
        return Score.from(this);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
