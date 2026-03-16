package domain;

import domain.card.Card;
import domain.card.Rank;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> hand;
    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_BONUS_SCORE = 10;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public List<String> toStringList() {
        return hand.stream()
                .map(Card::toString)
                .toList();
    }

    private int sumScore() {
        int score = 0;
        for (Card card : hand) {
            score += card.getRankValue();
        }
        return score;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE;
    }

    public int size() {
        return hand.size();
    }

    public int calculateScore() {
        int currentScore = sumScore();

        if (!hasAce()) {
            return currentScore;
        }

        if (!canApplyAceBonus(currentScore)) {
            return currentScore;
        }

        return currentScore + ACE_BONUS_SCORE;
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    private boolean canApplyAceBonus(int currentScore) {
        return currentScore + ACE_BONUS_SCORE <= BLACKJACK_SCORE;
    }
}
