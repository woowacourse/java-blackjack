package blackjack.model;

public class Dealer extends Participant {

    private static final int DRAW_UPPER_BOUND = 16;

    public boolean shouldDraw() {
        return hand.calculateScore() <= DRAW_UPPER_BOUND;
    }
}
