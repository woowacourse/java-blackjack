package blackjack.domain.participant;

import blackjack.domain.deck.Deck;
import java.util.function.BooleanSupplier;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean attemptDraw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            addCard(deck.draw());
            return true;
        }
        return false;
    }

    @Override
    public boolean canDraw() {
        return !isBust();
    }
}
