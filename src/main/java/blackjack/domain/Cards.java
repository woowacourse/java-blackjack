package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int TARGET_SCORE = 21;
    private static final int DIFFERENCE_IN_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getTotalScore() {
        int totalScore = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getValue)
                .sum();

        for (int i = 0; i < countAce(); i++) {
            totalScore = changeAceScore(totalScore);
        }
        return totalScore;
    }

    private int changeAceScore(int totalScore) {
        if (totalScore > TARGET_SCORE) {
            totalScore -= DIFFERENCE_IN_ACE_SCORE;
        }
        return totalScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
