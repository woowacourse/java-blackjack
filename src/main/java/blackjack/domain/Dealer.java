package blackjack.domain;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DRAWABLE_BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public Score compete(Player player) {
        if (player.isBust()) {
            return Score.WIN;
        }

        if (this.isBust()) {
            return Score.LOSE;
        }

        return Score.compare(this.getTotalNumber(), player.getTotalNumber());
    }

    @Override
    public boolean isDrawable() {
        return getTotalNumber() <= DRAWABLE_BOUND;
    }
}
