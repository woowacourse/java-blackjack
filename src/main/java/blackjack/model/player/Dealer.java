package blackjack.model.player;

import blackjack.model.DealerState;
import blackjack.model.State;
import blackjack.model.card.CardDeck;
import java.util.List;


public class Dealer extends Participant {
    private final State state;

    public Dealer() {
        super("딜러");
        this.state = new DealerState();
    }

    private Dealer(final String name, final State state) {
        super(name);
        this.state = state;
    }

    public boolean canHit() {
        return state.state().equals("Hit");
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        String name = this.name;
        State state = this.state;
        for (int i = 0; i < Player.START_DRAW_COUNT; i++) {
            state = this.state.addCard(deck.draw());
        }
        return new Dealer(name, state);
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        String name = super.name;
        State state = this.state.addCard(deck.draw());
        return new Dealer(name, state);
    }

    public List<String> getCards(){
        return state.getCards();
    }
}
