package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;

public abstract class PlayerStatus {
    private final Hand hand;

    protected PlayerStatus(Hand hand) {
        this.hand = hand;
    }

    public static PlayerStatus checkInitialBlackJack(Hand hand) {
        if (hand.isBlackJack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }

    public abstract PlayerStatus draw(Card card);

    public abstract PlayerStatus stay();

    public abstract boolean isRunning();

    protected final Hand hand() {
        return hand;
    }
}
