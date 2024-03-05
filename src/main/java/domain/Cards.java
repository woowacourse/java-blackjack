package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumAll() {
        int sum = calculateSum();

        if (hasAce()) {
            return sumAllWithBonusScore(sum);
        }
        return sum;
    }

    private int calculateSum() {
        return cards.stream()
                .map(Card::getDenomination)
                .map(Denomination::getScore)
                .reduce(0, Integer::sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int sumAllWithBonusScore(int sum) {
        int sumWithBonusScore = sum + ACE_BONUS_SCORE;

        if (sumWithBonusScore <= BLACKJACK_SCORE) {
            return sumWithBonusScore;
        }
        return sum;
    }
}
