package blackjack.domain.state;

import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.LOSE;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;

public class Bust extends Finished {

    Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (another instanceof Bust) {
            return DRAW;
        }
        return LOSE;
    }
}
