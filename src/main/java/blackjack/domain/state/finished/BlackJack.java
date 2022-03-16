package blackjack.domain.state.finished;

import static blackjack.domain.game.GameOutcome.BLACKJACK_WIN;
import static blackjack.domain.game.GameOutcome.DRAW;

import blackjack.domain.card.Cards;
import blackjack.domain.game.BattingMoney;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;

public class BlackJack extends Finished {

    public BlackJack(final Cards cards, final BattingMoney battingMoney) {
        super(cards, battingMoney);
    }

    @Override
    GameOutcome compare(final State another) {
        if (another.isSameStateWith(BlackJack.class)) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }
}
