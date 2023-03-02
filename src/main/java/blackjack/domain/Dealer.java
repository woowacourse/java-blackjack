package blackjack.domain;

public class Dealer extends Participant {


    public static final int CARD_DRAW_POINT = 16;


    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= CARD_DRAW_POINT;
    }
}
