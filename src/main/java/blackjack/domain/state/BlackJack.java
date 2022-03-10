package blackjack.domain.state;

import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;

public class BlackJack extends Finished {

    BlackJack(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (another instanceof BlackJack) {
            return DRAW;
        }
        return WIN;
    }
}
