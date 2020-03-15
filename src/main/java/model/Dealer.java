package model;

import java.util.*;

import static controller.BlackJackGame.HIT_BOUNDARY;
import static model.Player.DELIMITER;

public class Dealer extends User {
    public static final int ZERO = 0;

    public Dealer(CardHand cardHand) {
        super(cardHand);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(ZERO).toString();
    }

    public boolean isHitBound() {
        return getScore() <= HIT_BOUNDARY;
    }
}
