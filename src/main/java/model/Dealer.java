package model;

import java.util.*;

import static controller.BlackJackGame.HIT_BOUNDARY;
import static model.Player.DELIMITER;

public class Dealer extends User {
    public static final int ZERO = 0;

    private final Map<Result, Integer> result = new HashMap<>();

    public Dealer(CardHand cardHand) {
        super(cardHand);
        result.put(Result.WIN, ZERO);
        result.put(Result.DRAW, ZERO);
        result.put(Result.LOSE, ZERO);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(ZERO).toString();
    }

    public Map<Result, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public void setResult(final Result result) {
        Result oppositeResult = Result.oppositeResult(result);
        int count = this.result.get(oppositeResult);
        this.result.put(oppositeResult, count + 1);
    }

    public boolean isHitBound() {
        return getScore() <= HIT_BOUNDARY;
    }
}
