package domain;

public class Dealer extends User {

    private static final int UNDER_OVER_SCORE = 17;

    @Override
    boolean isHittable() {
        return cards.isUnder(UNDER_OVER_SCORE);
    }
}
