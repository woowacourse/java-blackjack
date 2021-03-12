package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.state.HandState;
import blackjack.domain.player.state.NonBlackJackHandState;
import blackjack.domain.result.Result;

import java.util.List;

public class Challenger extends Player {

    private HandState handState;

    public Challenger(final Cards cards, final Name name) {
        super(cards, name);
        handState = new NonBlackJackHandState();
    }

    public Result getResult(final Dealer dealer) {
        if (isBust()) {
            return Result.LOSE;
        }
        handState.blackJackCheck(dealer, this);
        handState.compareCards(dealer, this);
        return handState.getResult();
    }

    public void changeState(final HandState newHandSate) {
        this.handState = newHandSate;
    }

    @Override
    public List<Card> getInitCards() {
        return hand.getList();
    }
}
