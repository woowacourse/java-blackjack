package blackjack.model.trumpcard;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int SCORE_LIMIT = 21;
    private static final int SCORE_ACE_ADVANTAGE = 10;
    private static final int SCORE_ADVANTAGE_CRITERIA = SCORE_LIMIT - SCORE_ACE_ADVANTAGE;

    private final List<TrumpCard> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public int sumScore() {
        int score = 0;
        score = sumCardNumbersTo(score);
        if (hasAce()) {
            return sumAceAdvantageTo(score);
        }
        return score;
    }

    private int sumCardNumbersTo(int score) {
        for (TrumpCard card : cards) {
            score = card.sumNumberTo(score);
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.isNumber(TrumpNumber.ACE));
    }

    private int sumAceAdvantageTo(int score) {
        for (int i = 0; i < countAce(); i++) {
            score += choiceAceAdvantage(score);
        }
        return score;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isNumber(TrumpNumber.ACE))
                .count();
    }

    private int choiceAceAdvantage(int score) {
        if (score <= SCORE_ADVANTAGE_CRITERIA) {
            return SCORE_ACE_ADVANTAGE;
        }
        return 0;
    }

    public void add(TrumpCard card) {
        this.cards.add(card);
    }

    public boolean isBust() {
        return sumScore() > SCORE_LIMIT;
    }

    public boolean isScoreLessThan(int otherScore) {
        return sumScore() < otherScore;
    }

    public List<TrumpCard> getCards() {
        return cards;
    }

    public int getSize() {
        return this.cards.size();
    }
}
