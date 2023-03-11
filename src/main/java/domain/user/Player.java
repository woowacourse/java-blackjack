package domain.user;

import domain.money.BettingAmount;

public class Player extends User {

    private static final int DRAWABLE_LIMIT_SCORE = 21;

    private final PlayerName name;
    private BettingAmount bettingAmount;

    public Player(final String name) {
        this.name = new PlayerName(name);
    }

    public void betting(final int bettingAmount) {
        this.bettingAmount = BettingAmount.valueOf(bettingAmount);
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isRightName(final String name) {
        return new PlayerName(name).equals(this.name);
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }

    @Override
    public boolean isDrawable() {
        if (!state.isDrawable()) {
            return false;
        }
        int score = state.getScore();
        return score < DRAWABLE_LIMIT_SCORE;
    }

    public String getName() {
        return name.getName();
    }
}
