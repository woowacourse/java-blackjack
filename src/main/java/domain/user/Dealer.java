package domain.user;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    public String getFirstDrawResult() {
        return DEALER + CARD + cards.getFirstCard().getName();
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint()
                && cards.calculatePointAccordingToHasAce() < PIVOT;
    }
}
