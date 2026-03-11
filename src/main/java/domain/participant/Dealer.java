package domain.participant;

public class Dealer extends Participant {
    private static final int DEALER_MAX_DRAW_SCORE = 16;

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        int score = super.score();
        return score <= DEALER_MAX_DRAW_SCORE;
    }
}
