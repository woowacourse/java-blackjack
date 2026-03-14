package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCard {

    private static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int ACE_MAX_VALUE = 11;
    private static final int ACE_MIN_VALUE = 1;
    private static final int INITIAL_CARD_COUNT = 2;

    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public int score() {
        int nonAceTotal = cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return cardCalculatorWithAce(nonAceTotal, aceCount);
    }

    private int cardCalculatorWithAce(int nonAceTotal, int aceCount) {
        int total = nonAceTotal + (aceCount * ACE_MAX_VALUE);
        int remainingAce = aceCount;
        while (total > BLACKJACK_MAX_LIMIT && remainingAce > 0) {
            total -= (ACE_MAX_VALUE - ACE_MIN_VALUE);
            remainingAce--;
        }
        return total;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> cards() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    public boolean isBust() {
        return score() > BLACKJACK_MAX_LIMIT;
    }

    public boolean isBlackJack() {
        return cards.size() == INITIAL_CARD_COUNT && score() == BLACKJACK_MAX_LIMIT;
    }

    public List<String> getOpenCards(int openCardCount) {
        return cards.stream()
                .map(Card::getCardInfo)
                .limit(openCardCount)
                .toList();
    }

}
