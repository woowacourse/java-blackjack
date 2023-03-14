package domain.participant;

public class Player extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 21;

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        int score = getScoreSum();
        return score > 0 && score <= LIMIT_TAKE_CARD_VALUE;
    }
}
