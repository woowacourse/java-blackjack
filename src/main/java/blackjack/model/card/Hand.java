package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BUST_LOWER_BOUND = 22;
    private static final int ACE_ADJUST_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public boolean isBust() {
        return calculateScore() >= BUST_LOWER_BOUND;
    }

    public int calculateScore() {
        int scoreBeforeAdjust = getScoreBeforeAdjust();

        return adjust(scoreBeforeAdjust, cards);
    }

    private int getScoreBeforeAdjust() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int adjust(int scoreBeforeAdjust, List<Card> cards) {
        boolean containAce = cards.stream()
                .anyMatch(Card::isAce);
        int scoreAfterAdjust = scoreBeforeAdjust + ACE_ADJUST_SCORE;

        if (isNotBust(scoreAfterAdjust) && containAce) {
            return scoreAfterAdjust;
        }

        return scoreBeforeAdjust;
    }

    private boolean isNotBust(int score) {
        return score <= BUST_LOWER_BOUND;
    }
}
