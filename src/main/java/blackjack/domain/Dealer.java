package blackjack.domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean draw(Deck deck) {
        if (shouldDraw()) {
            Card card = deck.draw();
            hands.addCard(card);
            return true;
        }
        return false;
    }

    public String getFirstCardName(){
        return hands.getHands()
                .get(0)
                .getCardName();
    }

    private boolean shouldDraw() {
        return hands.getHandsScore() <= DEALER_DRAW_THRESHOLD;
    }
}
