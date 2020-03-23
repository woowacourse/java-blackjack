package model.user;

import java.util.List;
import model.card.Card;

public class Dealer extends BlackJackGameUser {
    private static final int FIRST = 0;
    public static final String DEALER_NAME = "딜러";
    public static final int HIT_BOUNDARY = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(FIRST).toString();
    }

    public boolean isHitBound() {
        return getScore() <= HIT_BOUNDARY;
    }
}