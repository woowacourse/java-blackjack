package participants;

import java.util.Map;

import blackjackgame.Result;
import card.Card;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private final DealerResult dealerResult = new DealerResult();


    public Dealer() {
        super(new Name(DEALER_NAME), new Hand());
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }

    public boolean isUnderScore() {
        return hand.calculateScore().isUnderScore();
    }

    @Override
    protected void winBlackjack() {
        dealerResult.addWin();
    }

    @Override
    public void win() {
        dealerResult.addWin();
    }

    @Override
    public void lose() {
        dealerResult.addLose();
    }

    @Override
    public void tie() {
        dealerResult.addTie();
    }

    public Map<Result, Count> getDealerResult() {
        return dealerResult.getDealerResult();
    }
}
