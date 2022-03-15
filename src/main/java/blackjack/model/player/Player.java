package blackjack.model.player;

import blackjack.model.DealerState;
import blackjack.model.PlayerState;
import blackjack.model.State;
import blackjack.model.card.CardDeck;
import java.util.List;

public class Player extends Participant {
    public static final String HIT = "Hit";

    private final State state;

    public Player(final String name) {
        super(name);
        this.state = new PlayerState();
    }

    private Player(final String name, final State state) {
        super(name);
        this.state = state;
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        String name = this.name;
        State state = this.state;
        for (int i = 0; i < Player.START_DRAW_COUNT; i++) {
            state = this.state.addCard(deck.draw());
        }
        return new Player(name, state);
    }

    public boolean canHit() {
        return state.state().equals(HIT);
    }

    public List<String> getCards(){
        return state.getCards();
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        String name = this.name;
        State state = this.state.addCard(deck.draw());
        return new Player(name, state);
    }
}
