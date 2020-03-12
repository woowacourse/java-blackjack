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

    @Override
    public String getDrawResult() {
        return DEALER + "카드: " + cards.get(0).getName();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !isBust() && !isBlackJack() && !isBlackJackPoint() && calculatePointAccordingToHasAce() < PIVOT;
    }
}
