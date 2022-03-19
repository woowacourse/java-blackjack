package blackjack.domain.state.started.finished;

import blackjack.domain.participant.Cards;

public final class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }
}
