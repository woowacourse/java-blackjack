package domain.participants;


public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    public boolean isOverDealerStandard() {
        return super.getCardsSum() > BOUND;
    }
}
