package blackjack.domain.participant;

public class Player extends Participant {
    private static final int MAX_SCORE_TO_RECEIVE = 21;

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean isAbleToReceive() {
        return score.getScore() <= MAX_SCORE_TO_RECEIVE;
    }

    @Override
    public String getName() {
        return name.getName();
    }
}
