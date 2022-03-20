package blackjack.domain.user.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Hand;

public final class Ready extends Running {

    private Ready(Hand hand) {
        super(hand);
    }

    public Ready() {
        this(new Hand());
    }

    @Override
    public State hit(Card card) {
        Hand hand = this.hand.addCard(card);
        if (hand.isBlackJack()) {
            return new BlackJack(hand);
        }
        if (hand.size() == Hit.SIZE) {
            return new Hit(hand);
        }
        return new Ready(hand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("준비 상태에서는 스테이를 할 수 없습니다.");
    }
}
