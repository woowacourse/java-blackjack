package blackjack.domain;

public class Dealer extends Participant {

    private static final int BUST_POINT = 21;
    private static final int CARD_DRAW_POINT = 16;


    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= CARD_DRAW_POINT;
    }
}
