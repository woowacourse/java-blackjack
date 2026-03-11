package blackjack.domain;


public class Dealer extends Participant {
    private static final int DEALER_DRAW_LIMIT = 17;

    public Dealer() {
    }

    public String getOneCardName() {
        return getCardNames().getFirst();
    }

    public int getAdditionalDrawnCardCount() {
        return getCardNames().size() - 2;
    }

    public boolean isDealerDone() {
        return calculateTotalScore() >= DEALER_DRAW_LIMIT;
    }
}
