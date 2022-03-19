package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.LOSE;
import static blackjack.domain.game.GameOutcome.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.game.betting.BettingMoney;
import blackjack.domain.state.State;

public class Stay extends Finished {

    public Stay(final Cards cards, final BettingMoney bettingMoney) {
        super(cards, bettingMoney);
    }

    @Override
    GameOutcome compare(final State another) {
        if (another.isSameStateWith(Blackjack.class)) {
            return LOSE;
        }
        if (another.isSameStateWith(Bust.class)) {
            return WIN;
        }
        return cards.isHigherThan(another.cards());
    }
}
