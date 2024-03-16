package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.state.InitState;
import blackjack.domain.rule.state.State;

public abstract class User {

    private final UserName userName;
    private State state;

    protected User(final UserName userName) {
        this.userName = userName;
        this.state = new InitState();
    }

    protected abstract boolean wantToHit();

    public final void playTurn(final Deck deck) {
        if (state.isInit() || wantToHit()) {
            state = state.draw(deck.pick());
            return;
        }
        state = state.stand();
    }

    public Hands openedHands() {
        return getHands();
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isDealer() {
        return false;
    }

    public final boolean isInitState() {
        return state.isInit();
    }

    public final boolean isFinished() {
        return state.isFinished();
    }

    public final boolean isNotFinished() {
        return !isFinished();
    }

    public final boolean isBustState() {
        return state.isBust();
    }

    public final Hands getHands() {
        return state.hands();
    }

    public final UserName getUserName() {
        return userName;
    }

    public final State getState() {
        return state.copy();
    }
}
