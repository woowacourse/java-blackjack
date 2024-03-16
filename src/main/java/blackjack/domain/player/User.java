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

    public void playTurn(final Deck deck) {
        if (state.isInit() || wantToHit()) {
            state = state.draw(deck.pick());
            return;
        }
        state = state.stand();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public boolean isInitState() {
        return state.isInit();
    }

    public Hands getOpenedHands() {
        return getHands();
    }

    public Hands getHands() {
        return state.hands();
    }

    public UserName getUserName() {
        return userName;
    }

    public State getState() {
        return state.copy();
    }
}
