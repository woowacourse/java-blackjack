package domain.card;

import static domain.Blackjack.PERFECT_SCORE;

import java.util.List;

public class Hands {
    private static final int ACE_HIGH = 11;

    private final List<Card> value;

    public Hands(final List<Card> value) {
        this.value = value;
    }

    public void add(final Card card) {
        value.add(card);
    }

    public int calculateScore() {
        int score = 0;
        for (final Card card : value) {
            score += determineScore(card, score);
        }
        return score;
    }

    private int determineScore(final Card card, final int score) {
        if (card.getRank() == Rank.ACE) {
            return determineAceScore(score);
        }
        return card.getRankValue();
    }

    private int determineAceScore(final int score) {
        if (score + ACE_HIGH <= PERFECT_SCORE) {
            return ACE_HIGH;
        }
        return Rank.ACE.getValue();
    }

    public List<Card> getValue() {
        return value;
    }
}
