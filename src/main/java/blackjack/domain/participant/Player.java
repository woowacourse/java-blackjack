package blackjack.domain.participant;

import blackjack.domain.deck.Deck;
import java.util.function.BooleanSupplier;

public class Player extends Participant {

    private final BetMoney betMoney;

    public Player(String name, int betMoney) {
        super(name);
        this.betMoney = new BetMoney(betMoney);
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
