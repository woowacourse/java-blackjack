package blackjack.domain.participant;

import blackjack.domain.deck.Deck;
import java.util.function.BooleanSupplier;

public class Player extends Participant {

    private final BetMoney betMoney;

    public Player(String name, BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public boolean attemptDraw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            addCard(deck.draw());
            return true;
        }
        return false;
    }

    public int getBetMoney() {
        return betMoney.getBetMoney();
    }

    @Override
    public boolean canDraw() {
        return !isBust();
    }
}
