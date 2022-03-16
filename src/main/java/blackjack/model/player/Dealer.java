package blackjack.model.player;

import blackjack.model.DealerState;
import blackjack.model.State;
import blackjack.model.card.CardDeck;
import java.net.PortUnreachableException;
import java.util.List;


public class Dealer extends Participant {
    private static final String NAME = "딜러";

    private final State state;

    public Dealer() {
        super(NAME);
        this.state = new DealerState();
    }

    private Dealer(final State state) {
        super(NAME);
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
        return new Dealer(copyOfState);
    }

    @Override
    public boolean canHit() {
        return state.canHit();
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        State state = this.state.addCard(deck.draw());
        return new Dealer(state);
    }

    @Override
    public Participant getCopyInstance() {
        State state = this.state.getCopyInstance();
        return new Dealer(state);
    }

    @Override
    public State getState() {
        return this.state.getCopyInstance();
    }
}
