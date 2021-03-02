package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int ACE_BONUS_SCORE = 10;
    private static final int BOUND_OF_CHANGE_ACE_VALUE = 11;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList(cards);
    }

    public void add(Cards targetCards) {
        cards.addAll(targetCards.cards);
    }

    public int calculateScore() {
        int aceCounts = getAceCounts();
        int sum = cards.stream()
                       .mapToInt(Card::getScore)
                       .sum();
        for (int i = 0; i < aceCounts; i++) {
            sum += plusBonusAceScore(sum);
        }
        return sum;
    }

    private int plusBonusAceScore(int sum) {
        if (sum <= BOUND_OF_CHANGE_ACE_VALUE) {
            return ACE_BONUS_SCORE;
        }
        return 0;
    }

    private int getAceCounts() {
        return (int) cards.stream()
                          .filter(Card::isAce)
                          .count();
    }
}
