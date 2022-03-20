package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public final class Ready extends Running {

    public Ready() {
        this(new Hand());
    }

    private Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        if (hand.isHit()) {
            return new Hit(hand);
        }
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Ready(hand);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("[ERROR] 스테이를 지원하지 않습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
