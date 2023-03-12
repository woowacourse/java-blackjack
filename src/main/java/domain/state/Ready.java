package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class Ready extends State {
    public Ready() {
        super(new Hand());
    }

    public Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);

        if (getHand().isEmpty())
            return new Ready(newHand);
        if (newHand.isBlackjack())
            return new Blackjack(newHand);
        return new Hit(newHand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.");
    }
}
