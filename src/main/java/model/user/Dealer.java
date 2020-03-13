package model.user;

import java.util.*;
import model.card.CardHand;
import model.Result;

import static controller.BlackJackGame.HIT_BOUNDARY;

public class Dealer extends BlackJackPerson {
    private static final int ZERO = 0;
    private static final int ONE_PLUS = 1;
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

    public Result compareScore(Player player) {
        if (this.isBust() && player.isBust()) {
            return Result.DRAW;
        }
        if (this.isBust()) {
            return Result.LOSE;
        }
        if (player.isBust()) {
            return Result.WIN;
        }
        return Result.calculateResult(Integer.compare(getScore(), player.getScore()));
    }

    public Map<Result, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public void setResult(Result result) {
        this.result.put(result, this.result.get(result) + ONE_PLUS);
    }

    public boolean isHitBound() {
        return getScore() <= HIT_BOUNDARY;
    }
}
