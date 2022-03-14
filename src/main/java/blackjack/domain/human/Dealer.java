package blackjack.domain.human;

public class Dealer extends Human {

    private static final int AVAILABLE_POINT_FOR_ADD_CARD = 16;
    private static final String NAME = "딜러";
    

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isOneMoreCard() {
        return getPoint() <= AVAILABLE_POINT_FOR_ADD_CARD;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
