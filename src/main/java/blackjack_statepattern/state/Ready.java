package blackjack_statepattern.state;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;

public final class Ready extends Started {
    public Ready() {
        this(new Cards());
    }

    private Ready(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isReady() {
        return true;
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
        throw new IllegalArgumentException("[ERROR] 준비 상태에서는 중지할 수 없습니다.");
    }
}
