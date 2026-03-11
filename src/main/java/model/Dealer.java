package model;

public class Dealer extends Participant{
    public static final String DEALER_NAME = "딜러";
    private static final Integer CARD_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean canDraw() {
        return !super.isMoreThanScore(CARD_DRAW_THRESHOLD);
    }

    public String getFirstCard() {
        return super.getFirstCard();
    }
}
