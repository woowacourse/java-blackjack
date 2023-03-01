package domain;

public class Dealer extends User {

    private static final int UNDER_OVER = 17;

    @Override
    boolean isHittable() {
        return cards.isUnder(UNDER_OVER);
    }
}
