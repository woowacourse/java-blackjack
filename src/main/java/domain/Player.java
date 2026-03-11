package domain;

import java.util.function.Supplier;

public class Player extends Participant {
    public Player(String name, Card card1, Card card2) {
        super(name, card1, card2);
    }

    public void hit(Supplier<Card> cardSupplier) {
        Hand ownHand = this.hand;
        if (canHit(ownHand)) {
            ownHand.addCard(cardSupplier.get());
        }
    }

    private boolean canHit(Hand ownHand) {
        return !ownHand.isBust();
    }
}
