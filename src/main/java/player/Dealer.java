package player;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }
}
