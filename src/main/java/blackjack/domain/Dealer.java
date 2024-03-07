package blackjack.domain;

import java.util.function.BooleanSupplier;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void decideDraw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            Card card = deck.draw();
            hands.addCard(card);
        }
    }

    public Boolean shouldDraw() {
        return hands.getHandsScore() < DEALER_DRAW_THRESHOLD;
    }
}
