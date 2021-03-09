package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public abstract class Running extends AfterInitDraw {

    public Running(final List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
