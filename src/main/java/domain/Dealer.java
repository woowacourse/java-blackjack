package domain;


public class Dealer extends Participant{
    private static final String NAME = "딜러";
    private static final int BOUND = 16;

    public Dealer() {
        super(new Name(NAME), new Cards());
    }

    public boolean isOverDealerStandard() {
        return super.getCardsSum() > BOUND;
    }
}
