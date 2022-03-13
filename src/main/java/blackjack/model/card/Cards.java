package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int SCORE_LIMIT = 21;
    private static final int SCORE_ACE_ADVANTAGE = 10;
    private static final int SCORE_ADVANTAGE_CRITERIA = SCORE_LIMIT - SCORE_ACE_ADVANTAGE;
    private static final int FIRST_SIZE = 2;

    private final List<Card> values;

    public Cards(Card card1, Card card2) {
        this.values = new ArrayList<>();
    }

    public Cards() {
        this.values = new ArrayList<>();
    }

    public void add(Card card) {
        this.values.add(card);
    }

    public boolean isOverLimitScore() {
        return sumScore() > SCORE_LIMIT;
    }

    public boolean isSameWithLimitScore() {
        return sumScore() == SCORE_LIMIT;
    }

    public boolean hasTwoCard() {
        return values.size() == 2;
    }

    public boolean isScoreOverThan(int otherScore) {
        return sumScore() > otherScore;
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
        for (Card card : values) {
            score = card.sumNumberTo(score);
        }
        return score;
    }

    private boolean hasAce() {
        return values.stream()
                .anyMatch(card -> card.hasSameNumber(TrumpNumber.ACE));
    }

    private int sumAceAdvantageTo(int score) {
        for (int i = 0; i < countAce(); i++) {
            score += choiceAceAdvantage(score);
        }
        return score;
    }

    private int countAce() {
        return (int) values.stream()
                .filter(card -> card.hasSameNumber(TrumpNumber.ACE))
                .count();
    }

    private int choiceAceAdvantage(int score) {
        if (score <= SCORE_ADVANTAGE_CRITERIA) {
            return SCORE_ACE_ADVANTAGE;
        }
        return 0;
    }

    public List<Card> getValues() {
        return values;
    }
}
