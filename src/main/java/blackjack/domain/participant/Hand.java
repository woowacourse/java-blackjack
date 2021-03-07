package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    public static final int MAX_SCORE = 21;

    private final Set<Card> cards;

    public Hand() {
        this.cards = new HashSet<>();
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public int totalScore() {
        int totalScore = calculateScore();
        if (isBurst(totalScore)) {
            totalScore = calculateHardAceScore(totalScore);
        }
        return totalScore;
    }

    private int calculateScore() {
        return this.cards
            .stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private int calculateHardAceScore(int totalScore) {
        int aceCount = getSoftAceCount();
        while (isBurst(totalScore) && hasSoftAce(aceCount)) {
            totalScore -= 10;
            aceCount -= 1;
        }
        return totalScore;
    }

    private int getSoftAceCount() {
        return (int) this.cards
            .stream()
            .filter(Card::isAce)
            .count();
    }

    public boolean isBurst(final int score) {
        return score > MAX_SCORE;
    }

    private boolean hasSoftAce(final int aceCount) {
        return aceCount > 0;
    }

    public List<Card> toList() {
        return new ArrayList<>(this.cards);
    }
}
