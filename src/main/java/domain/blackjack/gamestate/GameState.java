package domain.blackjack.gamestate;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public abstract class GameState {
    static final int DEFAULT_EARN_RATE = 1;

    protected final Cards cards;
    protected final HandState handState;

    public GameState(Cards cards, HandState handState) {
        this.cards = cards;
        this.handState = handState;
    }

    public abstract GameState receive(Card card);

    public abstract boolean isAbleToReceiveCard();

    public abstract Result competeToOtherState(GameState otherState);

    public abstract double getEarningRate();

    public Cards getCards() {
        return cards;
    }
}
