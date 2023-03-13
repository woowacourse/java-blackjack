package domain.blackjack.gamestate;

import domain.card.Card;
import domain.card.Cards;

public abstract class GameState {
    protected final Cards cards;

    public GameState(Cards cards) {
        this.cards = cards;
    }

    public abstract GameState receive(Card card);

    public abstract boolean isAbleToReceiveCard();

    public abstract double getEarningRate();

    public Cards getCards() {
        return cards;
    }
}
