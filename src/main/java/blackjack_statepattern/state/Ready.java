package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public final class Ready extends Running {
    public Ready() {
        this(new Cards());
    }

    private Ready(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards cards = cards().add(card);
        if (cards.isReady()) {
            return new Ready(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("아직 준비중인 상태입니다.");
    }
}
