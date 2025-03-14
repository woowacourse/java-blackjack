package game;

import card.Card;
import card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BLACK_JACK_BOUND = 21;
    private static final int VALUE_TO_SOFT_ACE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void drawCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public boolean isBust() {
        return calculate() > BLACK_JACK_BOUND;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculate() == BLACK_JACK_BOUND;
    }

    public int calculate() {
        int totalCardNumber = calculateTotalCardNumber();
        if (hasAce()) {
            return calculateWithAce(totalCardNumber);
        }
        return totalCardNumber;
    }

    private int calculateTotalCardNumber() {
        return cards.stream()
                .mapToInt(card -> card.cardNumber().getNumber())
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.cardNumber() == CardNumber.ACE);
    }

    private int calculateWithAce(int totalCardNumber) {
        int totalWithAce = totalCardNumber + VALUE_TO_SOFT_ACE;
        if (totalWithAce <= BLACK_JACK_BOUND) {
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
