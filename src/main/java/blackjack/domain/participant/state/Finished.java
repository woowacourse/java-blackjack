package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;

public abstract class Finished extends AfterInit {

    public Finished(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
