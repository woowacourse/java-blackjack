package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

public class Player extends Participant {

    public Player(Name name, State currentState) {
        super(name, currentState);
    }

    @Override
    public void draw(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    @Override
    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnStandState();
        }
    }

    @Override
    public ResultState resultState() {
        if (isBlackjack()) {
            return ResultState.BLACKJACK;
        }
        if (isBust()) {
            return ResultState.PLAYER_BUST;
        }
        return ResultState.STAND;
    }

    public WinningResult winningResult(CardScore dealerScore) {
        CardScore playerScore = cardScore();
        if (playerScore.compareTo(dealerScore) > 0) {
            return new WinningResult().win();
        }
        if (playerScore.compareTo(dealerScore) < 0) {
            return new WinningResult().lose();
        }
        return new WinningResult().draw();
    }
}
