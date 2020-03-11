package domain.user;

public class Dealer extends User {

    private static final String DEALER = "딜러";

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    @Override
    public String getFirstDrawResult() {
        return DEALER + ": " + cards.get(0).getName();
    }
}
