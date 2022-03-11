package blackjack.domain;


import java.util.List;

public class Dealer extends Gamer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    public boolean isDrawable() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }
}
