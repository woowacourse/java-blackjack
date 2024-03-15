package domain.card;

import static domain.card.Rank.SOFT_ADDITION_SCORE;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
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
