package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    protected static final int BUSTED_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public String getFirstSnapshot() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("남은 카드가 없습니다.");
        }
        return cards.getFirst().getDisplayName();
    }

    public String getSnapshot() {
        return cards
                .stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }

    public String addCard(List<Card> receivedCards) {
        cards.addAll(receivedCards);
        return getSnapshot();
    }

    public boolean isBusted() {
        int totalScore = getTotalScore();
        return totalScore >= BUSTED_SCORE;
    }

    public boolean isBlackjack() {
        return getTotalScore() == BUSTED_SCORE;
    }

    public int getTotalScore() {
        int scoreSum = calculateScoreSum();
        if (scoreSum < BUSTED_SCORE) {
            return scoreSum;
        }
        return calculateBustedScore(scoreSum);
    }

    private int calculateBustedScore(int scoreSum) {
        int aceCount = countAce();
        if (aceCount > 0) {
            return calculateAce(scoreSum, aceCount);
        }
        return scoreSum;
    }

    private int calculateScoreSum() {
        return cards
                .stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards
                .stream()
                .filter(Card::isAce)
                .count();
    }

    public int calculateAce() {
        int scoreSum = calculateScoreSum();
        int aceCount = countAce();
        return calculateAce(scoreSum, aceCount);
    }

    private int calculateAce(int scoreSum, int aceCount) {
        if (aceCount == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= BUSTED_SCORE) {
            return calculatedScore;
        }
        return calculateAce(calculatedScore, aceCount - 1);
    }
}
