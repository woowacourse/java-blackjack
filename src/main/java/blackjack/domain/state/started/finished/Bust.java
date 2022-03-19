package blackjack.domain.state.started.finished;

import blackjack.domain.participant.Cards;

public final class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }
}
