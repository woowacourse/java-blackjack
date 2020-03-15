package domain.user;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public String getFirstCard() {
        return cards.get(0).getName();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !isBust() && !isBlackJack() && !isBlackJackPoint() && calculatePoint() < PIVOT;
    }
}
