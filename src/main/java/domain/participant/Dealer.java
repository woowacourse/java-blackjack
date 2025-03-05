package domain.participant;

public class Dealer implements Participant {
    private static final int STAY_THRESHOLD = 17;
    private final String nickname;

    private Dealer(final String nickname) {
        this.nickname = nickname;
    }

    public static Dealer generate() {
        return new Dealer("딜러");
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < STAY_THRESHOLD;
    }

}
