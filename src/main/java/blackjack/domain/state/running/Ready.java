package blackjack.domain.state.running;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;

public final class Ready extends Running {

    public Ready() {
        super(new Cards());
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("아직 카드를 받지 않았습니다.");
    }
}
