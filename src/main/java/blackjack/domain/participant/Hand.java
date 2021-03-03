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
        cards.add(card);
    }

    public int totalScore() {
        int totalScore = calculateScore();
        if(isBurst(totalScore)) {
            totalScore = calculateHardAceScore(totalScore);
        }
        return totalScore;
    }

    private int calculateScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .reduce(0, Integer::sum);
    }

    private int calculateHardAceScore(int totalScore) {
        int aceCount = getAceCount();
        while (isBurst(totalScore) && hasAce(aceCount)) {
            totalScore -= 10;
            aceCount -= 1;
        }
        return totalScore;
    }

    private int getAceCount() {
        return (int) cards.stream()
            .filter(Card::isAce)
            .count();
    }

    private boolean hasAce(int aceCount) {
        return aceCount > 0;
    }

    private boolean isBurst(final int score) {
        return score > MAX_SCORE;
    }

    public List<Card> toList() {
        return new ArrayList<>(cards);
    }
}
