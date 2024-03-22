package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_SCORE = 21;
    public static final int INITIAL_CARD_COUNT = 2;

    private static final int SOFT_ADDITION_SCORE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.rank() == Rank.ACE);
    }

    public int score() {
        int softScore = sum() + SOFT_ADDITION_SCORE;
        if (hasAce() && softScore <= BLACKJACK_SCORE) {
            return softScore;
        }
        return sum();
    }

    public Card peek() {
        return cards.get(0);
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
