package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.state.InitState;
import blackjack.domain.rule.state.State;

public abstract class User {

    private final PlayerName playerName;
    private State state;

    protected User(final PlayerName playerName) {
        this.playerName = playerName;
        this.state = new InitState();
    }

    public abstract boolean canHit();
    public void draw(final Card card) {
        state = state.draw(card);
    }

    public void stand() {
        state = state.stand();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public boolean isStand() {
        return state.isStand();
    }

    public Hands getOpenedHands() {
        return getHands();
    }

    public Hands getHands() {
        return state.hands();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public State getState() {
        return state.copy();
    }
}
