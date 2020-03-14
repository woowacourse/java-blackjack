package model.user;

import model.card.CardHand;
import model.Result;

public class Player extends BlackJackPerson {
    private Result result;

    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }

    public void setResult(Result result) {
        this.result = Result.oppositeResult(result);
    }

    public Result getResult() {
        return result;
    }
}
