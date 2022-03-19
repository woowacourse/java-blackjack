package blackjack.domain.state;

import java.util.Set;

import blackjack.domain.card.Card;

public final class Ready extends Started {

    public Ready() {
        this(new Cards(Set.of()));
    }

    public Ready(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards newCards = cards.add(card);

        if (newCards.isBlackjack()) {
            return new Blackjack(newCards);
        }

        if (newCards.isEndInit()) {
            return new Hit(newCards);
        }

        return new Ready(newCards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Ready 상태에선 Stay를 할 수 없습니다.");
    }
}
