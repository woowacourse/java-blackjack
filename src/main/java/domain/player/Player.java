package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.state.State;

public abstract class Player {

    private static final int INITIAL_DRAW_COUNT = 2;

    protected final String name;
    protected State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public void drawInitialCards(Deck deck) {
        for (int count = 0; count < INITIAL_DRAW_COUNT; count++) {
            hit(deck);
        }
    }

    public void hit(Deck deck) {
        this.state = state.hit(deck.drawCard());
    }

    public void stay() {
        this.state = state.stay();
    }

    public Cards openedCards() {
        return new Cards(
                allCards().getCards().stream()
                        .filter(Card::isOpened)
                        .toList()
        );
    }

    public Cards allCards() {
        return state.cards();
    }

    public String getName() {
        return name;
    }
}
