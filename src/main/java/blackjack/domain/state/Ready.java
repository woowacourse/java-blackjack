package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Ready implements State {

    private final Cards cards;

    private Ready(Cards cards) {
        this.cards = cards;
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
    public Cards cards() {
        return cards;
    }

    @Override
    public double earningRate(State state) {
        return 0;
    }
}
