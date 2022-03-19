package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;

public abstract class Running extends Started {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public abstract State stay();
}
