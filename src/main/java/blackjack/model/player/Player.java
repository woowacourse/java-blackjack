package blackjack.model.player;

import blackjack.model.PlayerState;
import blackjack.model.State;
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

    public List<String> getCards() {
        return state.getCards();
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        State copyOfState = null;
        for (int i = 0; i < Participant.START_DRAW_COUNT; i++) {
            copyOfState = this.state.addCard(deck.draw());
        }
        return new Player(this.name, copyOfState);
    }

    @Override
    public boolean canHit() {
        return state.canHit();
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        State copyOfState = this.state.addCard(deck.draw());
        return new Player(this.name, copyOfState);
    }
}
