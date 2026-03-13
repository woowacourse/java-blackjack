package model.participant;

import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.CardNumber;
import model.Cards;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final int BLACKJACK_SCORE = 21;
    private static final int FIRST_TURN_CARD_COUNT = 2;

    protected final String name;
    protected final Cards hands;

    protected Participant(String name) {
        this(name, new ArrayList<>());
    }

    public Participant(String name, List<Card> hands) {
        this.name = name;
        this.hands = Cards.of(hands);
    }

    public String getName() {
        return name;
    }

    public Cards draw(Card card) {
        return hands.add(card);
    }

    public int calculateScore() {
        int total = hands.calculateScore();
        int aceCardCount = hands.aceCount();
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
        return false;
    }

    public boolean isBlackJack() {
        return hands.size() == FIRST_TURN_CARD_COUNT && calculateScore() == BLACKJACK_SCORE;
    }

    public abstract List<String> open();
}
