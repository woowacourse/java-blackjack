package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.player.BetAmount;

public class Hit extends Running {
    public Hit(Hand cards) {
        super(cards);
    }

    public State draw(Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay(cards);
    }

    @Override
    public double profit(State state, BetAmount amount) {
        throw new UnsupportedOperationException("현 상태에서는 계산이 불가능합니다.");
    }
}
