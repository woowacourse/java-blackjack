package domain.participants;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int BOUND = 16;

    public Dealer() {
        super(new Name(NAME));
    }

    public boolean isOverDealerStandard() {
        return super.getCardsSum() > BOUND;
    }

    public boolean dealerWin(int playerSum){
        return this.getCardsSum() > playerSum;
    }
}
