package blackjack.domain.participant;

public class Player extends Participant {

    private static final int LIMIT_SCORE = 21;

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getValue();
    }

    @Override
    public boolean isOverLimitScore() {
        return getScore() > LIMIT_SCORE;
    }
}
