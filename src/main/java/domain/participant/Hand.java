package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACK_JACK = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isBust() {
        return score() > BLACK_JACK;
    }

    public int score() {
        int total = calculateRawTotal();
        int aceCount = countAce();

        while (total > BLACK_JACK && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int size() {
        return cards.size();
    }

    public int calculateRawTotal() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
