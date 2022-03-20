package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public class Bust extends State {
    Bust(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    State draw(Card card) {
        throw new IllegalStateException();
    }
}
