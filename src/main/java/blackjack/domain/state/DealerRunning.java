package blackjack.domain.state;

import blackjack.domain.card.Hand;

public final class DealerRunning extends Running implements State {

    private DealerRunning(final Hand cards) {
        super(cards);
    }

    public static State from(final Hand hand) {
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isDealerStay()) {
            return new Stay(hand);
        }
        return new DealerRunning(hand);
    }

    @Override
    public State receiveCards(final Hand hand) {
        cards.addAll(hand);
        if (cards().isBust()) {
            return new Bust(cards);
        }
        if (cards().isBlackjack()) {
            return new Blackjack(cards);
        }
        if (cards().isDealerStay()) {
            return new Stay(cards);
        }
        return this;
    }
}
