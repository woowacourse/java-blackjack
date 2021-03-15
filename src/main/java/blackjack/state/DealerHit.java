package blackjack.state;

import blackjack.domain.card.Card;

public class DealerHit extends Running {

    public DealerHit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);

        if (this.cards.isBust()) {
            return new Bust(cards);
        }
        if (this.cards.isOverDrawScore()) {
            return new Stay(cards);
        }

        return new DealerHit(cards);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("딜러는 16이상일 경우만 스테이상태가 됩니다.");
    }
}
