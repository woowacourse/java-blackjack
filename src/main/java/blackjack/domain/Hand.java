package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BUSTED_SCORE = 21;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(List<Card> drewCards) {
        cards.addAll(drewCards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBlackjack() {
        return getTotalScore() == BUSTED_SCORE;
    }

    public boolean isBusted() {
        int totalScore = getTotalScore();
        return totalScore > BUSTED_SCORE;
    }

    public int getTotalScore() {
        int scoreSum = calculateScoreSum();
        if (hasAce() && (scoreSum + 10 <= BUSTED_SCORE)) {
            return scoreSum + 10;
        }
        return scoreSum;
    }

    private int calculateScoreSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
