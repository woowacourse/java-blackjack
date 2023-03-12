package blackjack.domain;

public class Player extends Person {
    private static final int PLAYER_STOP_HIT_BOUND = 21;

    private final Name name;

    private Player(Name name) {
        super();
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(new Name(name));
    }

    @Override
    public boolean isHitPossible() {
        int score = getScore();
        return score < PLAYER_STOP_HIT_BOUND;
    }

    public String getName() {
        return name.getName();
    }
}
