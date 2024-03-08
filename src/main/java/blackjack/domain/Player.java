package blackjack.domain;

import java.util.function.BooleanSupplier;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean draw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            return addCard(deck.draw());
        }
        return false;
    }
}
