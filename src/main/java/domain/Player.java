package domain;

import java.util.Objects;
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
        if (!isFinished()) {
            hand.addCard(cardSupplier.get());
        }
    }

    public boolean isFinished() {
        return hand.isBust() || isStay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }

        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}