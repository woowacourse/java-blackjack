package model.participant;

import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.CardNumber;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";
    private static final int BLACKJACK_SCORE = 21;
    private static final int FIRST_TURN_CARD_COUNT = 2;

    private final String name;
    protected final List<Card> hands;

    protected Participant(String name) {
        this(name, new ArrayList<>());
    }

    public Participant(String name, List<Card> hands) {
        this.name = name;
        this.hands = hands;
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
        while (aceCardCount-- > 0 && total > BUST_THRESHOLD) {
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

    public boolean isBlackJack() {
        return hands.size() == FIRST_TURN_CARD_COUNT && calculateScore() == BLACKJACK_SCORE;
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
