package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public final class Blackjack implements State {

    private final Hands hands;

    public Blackjack(final Hands hands) {
        this.hands = hands;
    }

    @Override
    public State start(final Card first, final Card second) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public State draw(final Card card) {
        final Hands newHands = hands.addCard(card);
        return new Burst(newHands);
    }

    @Override
    public State stand() {
        return new Blackjack(hands);
    }

    @Override
    public Hands getHands() {
        return new Hands(hands.getCards());
    }
}
