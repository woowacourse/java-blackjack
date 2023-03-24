package blackjack.domain.participants.status.running;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;
import blackjack.domain.participants.status.stopped.Bust;
import blackjack.domain.participants.status.stopped.Stay;

public class Hit extends Running {

    public Hit() {
        this(new Hand());
    }

    public Hit(final Hand cards) {
        super(cards);
    }

    @Override
    public Status addCard(final Card card) {
        final Hand newCards = cards.addCard(card);
        if (newCards.isBust()) {
            return new Bust(newCards);
        }

        return new Hit(newCards);
    }

    @Override
    public Status stay() {
        return new Stay(cards);
    }

}
