package blackjack.model.state.running;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.state.finished.Blackjack;
import blackjack.model.state.State;

public final class Ready extends Running {

    public Ready() {
        super();
    }

    private Ready(final Cards cards) {
        super(cards);
    }

    @Override
    public State add(final Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        if (cards.canHit()) {
            return new Hit(cards);
        }
        return new Ready(cards);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 아직 카드를 두장 분배 받지 못했습니다.");
    }
}
