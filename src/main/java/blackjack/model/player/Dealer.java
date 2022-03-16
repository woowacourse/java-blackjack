package blackjack.model.player;

import blackjack.model.card.Cards;
import blackjack.model.state.DealerState;
import blackjack.model.state.State;
import blackjack.model.card.CardDeck;

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

    private Dealer(final Cards cards) {
        super(NAME, cards);
        this.state = null;
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        Cards copyOfCards = null;
        for (int i = 0; i < Cards.START_CARD_COUNT; i++) {
            copyOfCards = this.cards.add(deck.draw());
        }
        return new Dealer(copyOfCards);
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
    public State getState() {
        return this.state.getCopyInstance();
    }
}
