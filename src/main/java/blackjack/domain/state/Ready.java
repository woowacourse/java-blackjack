package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Ready extends Started {

    private Ready(Cards cards) {
        super(cards);
    }

    public Ready() {
        this(new Cards());
    }

    @Override
    public State draw(Card card) {
        cards.append(card);

        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        if (cards.isReady()) {
            return new Hit(cards);
        }

        return new Ready(cards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate(State state) {
        throw new IllegalStateException();
    }
}
