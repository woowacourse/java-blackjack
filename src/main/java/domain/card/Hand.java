package domain.card;

import static domain.card.Rank.SOFT_ADDITION_SCORE;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_SCORE = 21;
    public static final int INITIAL_CARD_COUNT = 2;

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

    public int score(final boolean isSoft) {
        if (isSoft && hasAce()) {
            return sum() + SOFT_ADDITION_SCORE;
        }
        return sum();
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
