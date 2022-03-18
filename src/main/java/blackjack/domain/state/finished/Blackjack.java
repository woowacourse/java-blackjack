package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.BLACKJACK_WIN;
import static blackjack.domain.game.GameOutcome.DRAW;

import blackjack.domain.card.Cards;
import blackjack.domain.game.BettingMoney;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;

public class Blackjack extends Finished {

    public Blackjack(final Cards cards, final BettingMoney bettingMoney) {
        super(cards, bettingMoney);
    }

    @Override
    GameOutcome compare(final State another) {
        if (another.isSameStateWith(Blackjack.class)) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }
}
