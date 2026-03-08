package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int ACE_BONUS_SCORE = 10;
    public static final int MAX_NON_BUST_SCORE = 21;
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> showHand() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public int calculateScore() {
        int totalScore = cards.stream().mapToInt(Card::getScore).sum();

        if (hasAceCard()) {
            return calculateAceBonus(totalScore);
        }

        return totalScore;
    }

    public boolean isBust() {
        return calculateScore() > MAX_NON_BUST_SCORE;
    }

    private int calculateAceBonus(int totalScore) {
        if (totalScore + ACE_BONUS_SCORE <= MAX_NON_BUST_SCORE) {
            return totalScore + ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean hasAceCard() {
        return cards.stream().anyMatch(Card::isAceCard);
    }
}
