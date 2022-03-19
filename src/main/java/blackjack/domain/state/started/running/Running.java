package blackjack.domain.state.started.running;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.started.Started;

public abstract class Running extends Started {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public abstract State stay();
}
