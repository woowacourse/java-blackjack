package domain.user;

public class Dealer extends User {

    public static final String NAME = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(NAME);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint()
                && cards.calculatePoint() < PIVOT;
    }
}
