package domain.gamer;

import domain.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BUST_NUMBER = 21;
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int getSumOfRank() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return getSumOfRank() > BUST_NUMBER;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
