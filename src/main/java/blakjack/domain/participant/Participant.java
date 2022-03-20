package blakjack.domain.participant;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.CardDeck;
import blakjack.domain.state.State;
import blakjack.domain.state.running.Init;

public abstract class Participant {
    private final State state;

    Participant(final PrivateArea privateArea, final Chip chip) {
        this.state = new Init(privateArea, chip);
    }

    public final void initCards(final CardDeck cardDeck) {
        state.draw(cardDeck.draw());
        state.draw(cardDeck.draw());
    }
}
