package blackjack.domain;

import java.util.function.BooleanSupplier;

public class Player extends Participant {
    private final Batting batting;

    public Player(Name name, Batting batting) {
        super(name);
        this.batting = batting;
    }

    public PlayerState draw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            addCard(deck.draw());
            return canNextDraw();
        }
        return PlayerState.FINISHED;
    }

    public boolean isFirstTurnBackJack() {
        return isBlackJack() && hands.getHands().size() == 2;
    }

    private PlayerState canNextDraw() {
        if (isBurst()) {
            return PlayerState.FINISHED;
        }
        return PlayerState.RUNNING;
    }

    public Double getBat() {
        return batting.getBat();
    }
}
