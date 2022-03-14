package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;

public class BlackJack extends Finished {

    public BlackJack(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (State.isSameState(another, BlackJack.class)) {
            return DRAW;
        }
        return WIN;
    }
}
