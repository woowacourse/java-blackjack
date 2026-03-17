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

    public int cardCalculate() {
        int nonAceTotal = cards.stream()
                .filter(c -> !c.isAce())
                .mapToInt(Card::getRankScore)
                .sum();
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return aceCalculate(nonAceTotal, aceCount);
    }

    private int aceCalculate(int nonAceTotal, int aceCount) {
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

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCardInfo() {
        return cards.getFirst();
    }
}
