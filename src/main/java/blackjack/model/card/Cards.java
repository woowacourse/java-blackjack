package blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int SCORE_LIMIT = 20;
    private static final int SCORE_ACE_ADVANTAGE = 10;
    private static final int SCORE_ADVANTAGE_CRITERIA = SCORE_LIMIT - SCORE_ACE_ADVANTAGE;
    private static final int FIRST_SIZE = 2;

    private final List<Card> cards;

    public Cards(Card card1, Card card2) {
        this.cards = new ArrayList<>();
    }

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public boolean isTotalScoreOverLimit() {
        return sumScore() > SCORE_LIMIT;
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
        for (Card card : cards) {
            score = card.sumNumberTo(score);
        }
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.hasSameNumber(TrumpNumber.ACE));
    }

    private int sumAceAdvantageTo(int score) {
        for (int i = 0; i < countAce(); i++) {
            score += choiceAceAdvantage(score);
        }
        return score;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.hasSameNumber(TrumpNumber.ACE))
                .count();
    }

    private int choiceAceAdvantage(int score) {
        if (score <= SCORE_ADVANTAGE_CRITERIA) {
            return SCORE_ACE_ADVANTAGE;
        }
        return 0;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public boolean isScoreLessThan(int otherScore) {
        return sumScore() < otherScore;
    }

    public String getFirstCardToString() {
        return this.cards.toString();
    }

    public List<String> getCardsToString() {
        return this.cards.stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    public int countAddedCards() {
        return this.cards.size() - FIRST_SIZE;
    }
}
