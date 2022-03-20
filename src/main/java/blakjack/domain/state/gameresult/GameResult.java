package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;

public abstract class GameResult extends State {
    GameResult(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public final State compare(State dealerState) {
        throw new IllegalStateException();
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public final State stay() {
        throw new IllegalStateException();
    }
}
