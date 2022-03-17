package blackjack.domain;

public class Player extends Participant {

    public static final int BLACKJACK_NUMBER = 21;

    private final BettingAmount bettingAmount;

    public Player(String name, int bettingAmount) {
        this(new Name(name), new BettingAmount(bettingAmount));
    }

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
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
