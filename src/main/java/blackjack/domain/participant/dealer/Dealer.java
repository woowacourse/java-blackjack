package blackjack.domain.participant.dealer;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Hand;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private final DealerResult dealerResult = new DealerResult();


    public Dealer() {
        super(new Name(DEALER_NAME), new Hand());
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }

    public boolean isUnderScore() {
        return hand.calculateScore() <= 16;
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
