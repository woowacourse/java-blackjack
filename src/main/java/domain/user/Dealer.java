package domain.user;

public class Dealer extends User{
    private static final String DEALER_NAME = "딜러";
    public static final int DRAW_MAX_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstStatus(){
        return getStatus().split(",")[0];
    }

    public boolean isUnderBound() {
        return getScore() <= DRAW_MAX_SCORE;
    }
}
