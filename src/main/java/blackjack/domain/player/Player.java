package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public abstract class Player {

    private final Name name;
    private State state;

    public Player(Name name, CardDeck cardDeck) {
        this.name = name;
        State state = new Ready()
            .draw(cardDeck.draw())
            .draw(cardDeck.draw());
    }

    public abstract boolean isHit();

    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    public int getPoint() {
        return state.cards().sum();
    }

    public void stay() {
        this.state = state.stay();
    }

    public State getState() {
        return state;
    }

    public Cards getCards() {
        return state.cards();
    }

    public String getName() {
        return name.getName();
    }
}
