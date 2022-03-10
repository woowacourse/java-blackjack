package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;

public class Running extends Created {

    Running(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public GameOutcome compare(final State another) {
        throw new IllegalStateException("턴이 종료되지 않아, 승무패 비교가 불가능합니다.");
    }
}
