package domain.blackjack.gamestate;

import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public abstract class GameState {
    static final int DEFAULT_EARN_RATE = 1;

    protected final Cards cards;

    public GameState(Cards cards) {
        this.cards = cards;
    }

    public abstract GameState receive(Card card);

    public abstract boolean isAbleToReceiveCard();

    public abstract Result competeToOtherState(GameState otherState);

    public abstract double getEarningRate();

    public Cards getCards() {
        return cards;
    }
}
