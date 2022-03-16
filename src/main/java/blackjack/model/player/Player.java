package blackjack.model.player;

import blackjack.model.card.Cards;
import blackjack.model.state.PlayerState;
import blackjack.model.state.State;
import blackjack.model.card.CardDeck;

import java.util.List;

public class Player extends Participant {
    private final State state;

    public Player(final String name) {
        super(name);
        this.state = new PlayerState();
    }

    private Player(final String name, final State state) {
        super(name);
        this.state = state;
    }

    private Player(final String name, final Cards cards) {
        super(name, cards);
        this.state = null;
    }

    public List<String> getCards() {
        return state.getCards();
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        Cards copyOfCards = null;
        for (int i = 0; i < Cards.START_CARD_COUNT; i++) {
            copyOfCards = this.cards.add(deck.draw());
        }
        return new Player(this.name, copyOfCards);
    }

    @Override
    public boolean canHit() {
        return state.canHit();
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        state.canHit();
        State copyOfState = this.state.addCard(deck.draw());
        return new Player(this.name, copyOfState);
    }

    @Override
    public Participant getCopyInstance() {
        State state = this.state.getCopyInstance();
        return new Player(name, state);
    }

    @Override
    public State getState() {
        return this.state.getCopyInstance();
    }
}
