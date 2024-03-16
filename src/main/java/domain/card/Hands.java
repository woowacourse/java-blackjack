package domain.card;

import static domain.Blackjack.PERFECT_SCORE;

import java.util.List;

public class Hands {
    private static final int ACE_SOFT_HARD_GAP = 10;

    private final List<Card> value;

    public Hands(final List<Card> value) {
        this.value = value;
    }

    public void add(final Card card) {
        value.add(card);
    }

    public int calculateScore() {
        final boolean hasAce = value.stream().anyMatch(card -> card.rank() == Rank.ACE);
        final int score = value.stream().mapToInt(Card::getRankValue).sum();

        if (hasAce && score + ACE_SOFT_HARD_GAP <= PERFECT_SCORE) {
            return score + ACE_SOFT_HARD_GAP;
        }
        
        return score;
    }

    public List<Card> getValue() {
        return value;
    }
}
