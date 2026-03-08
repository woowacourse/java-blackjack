package model.participant;

import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.CardNumber;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";
    private String name;
    protected List<Card> hands;

    protected Participant(String name) {
        this.name = name;
        this.hands = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public List<Card> draw(Card card) {
        hands.add(card);
        return List.copyOf(hands);
    }

    public int calculateScore() {
        int total = calculate();
        int aceCardCount = aceCount();
        while (aceCardCount-- > 0 && total > 21) {
            total -= 10;
        }

        return total;
    }

    public boolean isBust() {
        if (calculateScore() > BUST_THRESHOLD) {
            return true;
        }

        return false;
    }

    public boolean dealerNeedDraw() {
        return calculateScore() <= DEALER_THRESHOLD;
    }

    public boolean isDealer() {
        return name.equals(DEALER_NAME);
    }

    private int calculate() {
        return hands.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::toValue)
                .sum();
    }

    private int aceCount() {
        int count = 0;
        for (Card card : hands) {
            if (card.getCardNumber() == CardNumber.ACE) {
                count++;
            }
        }

        return count;
    }

    public abstract List<String> open();
}
