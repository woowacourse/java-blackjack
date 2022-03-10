package blackjack.domain.state;

import static blackjack.domain.game.GameOutcome.LOSE;
import static blackjack.domain.game.GameOutcome.WIN;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;

public class Stay extends Finished {

    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public GameOutcome compare(final State another) {
        if (another instanceof BlackJack) {
            return LOSE;
        } else if(another instanceof Bust) {
            return WIN;
        }
        return cards.isHigherThan(another.cards());
    }

}
