package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends AbstractState {
    Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        final var newCards = cards().draw(card);
        if (newCards.calculate() > 21) {
            return new Bust(newCards);
        }
        if (newCards.calculate() == 21) {
            return new Stand(newCards);
        }
        return new Hit(newCards);
    }

    @Override
    public State stand() {
        return new Stand(cards());
    }

    @Override
    public BlackjackStatus getStatus() {
        return BlackjackStatus.HIT;
    }
}
