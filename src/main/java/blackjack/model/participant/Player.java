package blackjack.model.participant;

import blackjack.model.BetAmount;
import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.state.PlayerDrawState;
import blackjack.model.state.State;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount, State currentState) {
        super(name, currentState);
        this.betAmount = betAmount;
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    public void changeToStand() {
        if (currentState instanceof PlayerDrawState) {
            this.currentState = ((PlayerDrawState) currentState).transitToStandState();
        }
    }

    public Result getResult(Dealer dealer) {
        return Result.checkPlayerResult(this, dealer);
    }
}
