package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;
    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand from(List<Card> cards) {
        return new Hand(cards);
    }

    public static Hand createEmptyHands() {
        return from(new ArrayList<>());
    }

    public Hand receive(List<Card> cards) {
        List<Card> copiedCards = new ArrayList<>(getCards());
        copiedCards.addAll(cards);
        return from(copiedCards);
    }

    public int calculateTotalScore() {
        boolean busted = isRawSumBusted();
        int scoreSum = calculateScoreSum();
        if (busted) {
            int aceCount = countAce();
            return calculateWithAce(scoreSum, aceCount);
        }
        return scoreSum;
    }

    public String getStatusByDisplayName() {
        return cards.stream().map(Card::getDisplayName).collect(Collectors.joining(", "));
    }

    public Card getFirstCard() {
        return getCards().getFirst();
    }

    public boolean isBlackJack() {
        List<Card> allCards = getCards();
        return allCards.size() == 2 && calculateTotalScore() == BLACKJACK_SCORE;
    }

    public boolean isDrawable() {
        return calculateTotalScore() < BLACKJACK_SCORE;
    }

    public boolean isBusted() {
        return calculateTotalScore() > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private boolean isRawSumBusted() {
        return calculateScoreSum() > BLACKJACK_SCORE;
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

    private int calculateWithAce(int scoreSum, int aceCount) {
        if (aceCount == 0) {
            return scoreSum;
        }
        int calculatedScore = scoreSum - 10;
        if (calculatedScore <= BLACKJACK_SCORE) {
            return calculatedScore;
        }
        return calculateWithAce(calculatedScore, aceCount - 1);
    }
}
