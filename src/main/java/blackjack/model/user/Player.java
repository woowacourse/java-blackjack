package blackjack.model.user;


import blackjack.model.constant.Constant;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < Constant.BLACKJACK_SCORE;
    }
}
