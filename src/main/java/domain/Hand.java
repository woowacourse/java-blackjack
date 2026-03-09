package domain;

import domain.constant.Rank;
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

    public List<Card> getHand() {
        return List.copyOf(hand);
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

    public int calculateScore() {
        int currentScore = sumScore();
        boolean hasACE = hand.stream()
                .anyMatch(c -> c.getRank() == Rank.ACE);

        if (!hasACE) {
            return currentScore;
        }

        if (currentScore + ACE_BONUS_SCORE > BLACKJACK_SCORE) {
            return currentScore;
        }

        return currentScore + ACE_BONUS_SCORE;
    }

    public boolean isBust() {
        return sumScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return sumScore() == BLACKJACK_SCORE;
    }
}
