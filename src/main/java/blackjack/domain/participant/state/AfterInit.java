package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public abstract class AfterInit implements State {

    protected final Hand hand;

    public AfterInit(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public List<Card> cards() {
        return this.hand.getCards();
    }

    @Override
    public Score score() {
        return this.hand.score();
    }
}
