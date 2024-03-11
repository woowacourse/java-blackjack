package blackjack.domain;

import java.util.function.BooleanSupplier;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public PlayerState draw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            addCard(deck.draw());
            return canNextDraw();
        }
        return PlayerState.FINISHED;
    }

    private PlayerState canNextDraw() {
        if (isBurst()) {
            return PlayerState.FINISHED;
        }
        return PlayerState.RUNNING;
    }
}
