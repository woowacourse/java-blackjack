package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.player.state.BlackJack;
import blackjack.domain.player.state.State;
import blackjack.domain.player.state.StateFactory;

public abstract class Player {

    private final String name;
    private State state;

    protected Player(String name, Card first, Card second) {
        this.name = name;
        this.state = StateFactory.start(Deck.of(first, second));
    }

    public void drawCard(Card card) {
        state = state.draw(card);
    }

    protected Score score() {
        return state.score();
    }

    public boolean isBlackJack() {
        return state instanceof BlackJack;
    }

    public String name() {
        return name;
    }

    public State state() {
        return state;
    }

    public abstract boolean drawable();

}
