package blackjack.domain.participant.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.Map;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private final DealerWinningResult dealerWinningResult = new DealerWinningResult();


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
        dealerWinningResult.addLose();
    }

    public void win() {
        dealerWinningResult.addWin();
    }

    public void tie() {
        dealerWinningResult.addTie();
    }

    public Map<WinningResult, Integer> getDealerResult() {
        return dealerWinningResult.getResultToCount();
    }
}
