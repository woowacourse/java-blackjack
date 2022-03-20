package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.finished.Bust;
import blakjack.domain.state.finished.Stay;

public final class Hit extends State {
    Hit(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State draw(final Card card) {
        privateArea.addCard(card);
        if (privateArea.isBust()) {
            return new Bust(privateArea, chip);
        }
        return new Hit(privateArea, chip);
    }

    @Override
    public State stay() {
        return new Stay(privateArea, chip);
    }
}
