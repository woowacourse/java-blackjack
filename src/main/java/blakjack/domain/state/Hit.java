package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public final class Hit extends State {
    Hit(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    State draw(final Card card) {
        privateArea.addCard(card);
        if (privateArea.isBust()) {
            return new Bust(privateArea, chip);
        }
        return new Hit(privateArea, chip);
    }
}
