package blackjack.domain.state.running;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;

public final class Ready extends Running {

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

        if (newCards.size() == 2) {
            return new Hit(newCards);
        }

        return new Ready(newCards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Ready 상태에선 Stay를 할 수 없습니다.");
    }
}
