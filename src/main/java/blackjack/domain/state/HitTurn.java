package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class HitTurn extends Started {
    HitTurn(Cards cards) {
        super(cards);
    }

    @Override
    public State stand() {
        return new Stand(getCards());
    }

    @Override
    public State hit(Card card) {
        getCards().add(card);
        if (isBust()) {
            return new Bust(getCards());
        }
        return new HitTurn(getCards());
    }
}
