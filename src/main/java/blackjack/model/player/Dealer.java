package blackjack.model.player;

public class Dealer extends Player {

    public static final int RECEIVABLE_POINT = 16;

    public boolean isDrawable() {
        return calculatePoint() <= RECEIVABLE_POINT;
    }
}
