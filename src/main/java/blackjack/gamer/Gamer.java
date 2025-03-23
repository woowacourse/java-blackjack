package blackjack.gamer;

import blackjack.card.CardDeck;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.ready.InitialDealing;

public abstract class Gamer {
    protected State state;

    public Gamer(Hand hand) {
        state = new InitialDealing(hand);
    }

    public int getScore() {
        return state.getHand().calculateTotalPoint();
    }

    public Hand getHand() {
        return state.getHand();
    }

    public abstract String getNickname();

    public abstract void initialDeal(CardDeck cardDeck);

    public void stay() {
        state = state.stay();
    }

    public void hit(CardDeck deck) {
        if (!state.isFinished()) {
            state = state.hit(deck.drawCard());
        }
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
