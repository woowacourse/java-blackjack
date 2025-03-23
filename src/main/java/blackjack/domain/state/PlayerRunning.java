package blackjack.domain.state;

import blackjack.domain.card.Hand;

public final class PlayerRunning extends Running implements State {

    private PlayerRunning(final Hand cards) {
        super(cards);
    }

    public static State from(final Hand hand) {
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new PlayerRunning(hand);
    }

    @Override
    public State receiveCards(final Hand hand) {
        cards.addAll(hand);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return this;
    }
}
