package domain.participant;

public class Dealer extends Participant {
    private static final int STAY_THRESHOLD = 16;

    private Dealer() {
        super("딜러");
    }

    public static Dealer generate() {
        return new Dealer();
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score <= STAY_THRESHOLD;
    }

    @Override
    public boolean areYouDealer() {
        return true;
    }

    public static int getStayThreshold() {
        return STAY_THRESHOLD;
    }
}
