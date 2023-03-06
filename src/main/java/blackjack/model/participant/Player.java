package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.result.ResultChecker;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

import java.util.HashMap;
import java.util.Map;

public class Player extends Participant {

    public Player(Name name, State currentState) {
        super(name, currentState);
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).transitToStandState();
        }
    }

    public Result getResult(Dealer dealer) {
        return new ResultChecker().checkPlayerResult(this, dealer);
    }
}
