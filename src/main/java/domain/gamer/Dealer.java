package domain.gamer;

public class Dealer extends Gamer {

    private static final int MIN_DEALER_SCORE = 16;

    public Dealer() {
        initCard();
    }

    public boolean shouldDraw() {
        return cards.getScoreByAceToOne() <= MIN_DEALER_SCORE;
    }
}
