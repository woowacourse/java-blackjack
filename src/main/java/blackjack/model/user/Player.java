package blackjack.model.user;

public class Player extends User {

    private static final int BLACKJACK_SCORE = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < BLACKJACK_SCORE;
    }
}
