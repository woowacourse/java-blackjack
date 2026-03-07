package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCard {
    private static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int ACE_MAX_VALUE = 11;
    private static final int ACE_MIN_VALUE = 1;

    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public int score() {
        int nonAceTotal = cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getRankScore)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return cardCalculatorWithAce(nonAceTotal, aceCount);
    }

    private int cardCalculatorWithAce(int nonAceTotal, int aceCount) {
        int totalSum = nonAceTotal + (aceCount * ACE_MAX_VALUE);
        int remainingAce = aceCount;
        while (totalSum > BLACKJACK_MAX_LIMIT && remainingAce > 0) {
            totalSum -= (ACE_MAX_VALUE - ACE_MIN_VALUE);
            remainingAce--;
        }
        return totalSum;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> printCardInfos() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    public String printFirstCardInfo() {
        return cards.getFirst()
                .getCardInfo();
    }

    public boolean isBust() {
        return score() > BLACKJACK_MAX_LIMIT;
    }
}
