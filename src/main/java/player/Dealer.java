package player;

import java.util.Map;

import blackjackgame.Result;
import card.Card;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    public static final int UNDER_SCORE = 16;
    private final DealerResult dealerResult = new DealerResult();


    public Dealer() {
        super(new Name(DEALER_NAME), new Hand());
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }

    public boolean isUnderScore() {
        return hand.calculateScore() <= UNDER_SCORE;
    }

    public void lose() {
        dealerResult.addLose();
    }

    public void win() {
        dealerResult.addWin();
    }

    public void tie() {
        dealerResult.addTie();
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }
}
