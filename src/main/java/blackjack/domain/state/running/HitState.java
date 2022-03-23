package blackjack.domain.state.running;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;

public final class HitState extends Running {
    public HitState(final Cards cards) {
        super(cards);
    }

    @Override
    public State addCard(final Card card) {
        cards.add(card);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new HitState(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
