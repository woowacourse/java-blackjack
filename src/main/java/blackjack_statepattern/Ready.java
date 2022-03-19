package blackjack_statepattern;

import java.util.List;

public final class Ready implements State {
    private final Cards cards;

    public Ready() {
        this(new Cards());
    }

    public Ready(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        Cards cards = this.cards.add(card);
        if (cards.isReady()) {
            return new Ready(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    public static State start(final Card first, final Card second) {
        Cards cards = new Cards(List.of(first, second));
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

}
