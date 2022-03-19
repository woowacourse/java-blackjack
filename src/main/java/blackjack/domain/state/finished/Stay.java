package blackjack.domain.state.finished;

import blackjack.domain.participant.Cards;

public final class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }
}
