package blackjack.domain;

public class Player extends Participant {

    public static final int BLACKJACK_NUMBER = 21;

    public Player(String name) {
        super(name);
    }

    public Player(Name name) {
        super(name);
    }

    @Override
    public Score compete(Participant player) {
        if (player.isBust()) {
            return getScoreWithBust();
        }

        if (this.isBust()) {
            return Score.LOSE;
        }

        return Score.compare(this.getTotalNumber(), player.getTotalNumber());
    }

    private Score getScoreWithBust() {
        if (this.isBust()) {
            return Score.LOSE;
        }
        return Score.WIN;
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }
}
