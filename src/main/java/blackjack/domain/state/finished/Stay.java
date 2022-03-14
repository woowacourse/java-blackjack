package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.LOSE;
import static blackjack.domain.game.GameOutcome.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;

public class Stay extends Finished {

    public Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (another.isSameStateWith(BlackJack.class)) {
            return LOSE;
        } else if (another.isSameStateWith(Bust.class)) {
            return WIN;
        }
        return cards.isHigherThan(another.cards());
    }
}
