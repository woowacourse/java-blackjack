package blackjack.model;


public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < Constant.BLACKJACK_SCORE;
    }
}
