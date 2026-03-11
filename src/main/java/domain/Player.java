package domain;

import java.util.function.Supplier;

public class Player extends Participant {
    private final boolean isStay;

    private Player(String name, Hand hand, boolean isStay) {
        super(name, hand);
        this.isStay = isStay;
    }

    public static Player from(String name, Hand hand) {
        return new Player(name, hand, false);
    }

    public Player stay() {
        return new Player(this.name, this.hand, true);
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