package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;

public final class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards newCards = cards.add(card);

        if (newCards.sum() > 21) {
            return new Bust(newCards);
        }

        return new Hit(cards.add(card));
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
