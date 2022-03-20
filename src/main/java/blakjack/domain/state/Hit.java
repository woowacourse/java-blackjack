package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public class Hit extends State {
    public Hit(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    State draw(Card card) {
        return null;
    }
}
