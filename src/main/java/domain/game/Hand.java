package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BUST_BOUND = 21;
    private static final int VALUE_TO_SOFT_ACE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void drawCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public boolean isOverBustBound() {
        return calculateTotalWithAce() > BUST_BOUND;
    }

    public int calculateTotalWithAce() {
        int totalCardNumber = calculateTotalCardNumber();
        if (hasAce()) {
            return calculateWithAce(totalCardNumber);
        }
        return totalCardNumber;
    }

    private int calculateTotalCardNumber() {
        return cards.stream()
                .mapToInt(card -> card.getCardNumber().getNumber())
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.ACE);
    }

    private int calculateWithAce(int totalCardNumber) {
        int totalWithAce = totalCardNumber + VALUE_TO_SOFT_ACE;
        if (totalWithAce <= BUST_BOUND) {
            return totalWithAce;
        }
        return totalCardNumber;
    }

    public int getCardsCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
