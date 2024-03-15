package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends AbstractState {
    Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        return null;
    }

    @Override
    public boolean isHit() {
        return true;
    }
}
