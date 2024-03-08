package blackjack.domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean draw(Deck deck) {
        if (shouldDraw()) {
            return addCard(deck.draw());
        }
        return false;
    }

    public String getFirstCardName() {
        return hands.getHands()
                .get(0)
                .getCardName();
    }

    private boolean shouldDraw() {
        return hands.getHandsScore() <= DEALER_DRAW_THRESHOLD;
    }
}
