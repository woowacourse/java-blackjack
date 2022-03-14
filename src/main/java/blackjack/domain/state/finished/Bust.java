package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.LOSE;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;

public class Bust extends Finished {

    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (another.isSameStateWith(Bust.class)) {
            return DRAW;
        }
        return LOSE;
    }
}
