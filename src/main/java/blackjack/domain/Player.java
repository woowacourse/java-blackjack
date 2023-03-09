package blackjack.domain;

public class Player extends Person {
    private static final int PLAYER_STOP_HIT_BOUND = 21;
    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < PLAYER_STOP_HIT_BOUND;
    }

    public String getName() {
        return name.getName();
    }
}
