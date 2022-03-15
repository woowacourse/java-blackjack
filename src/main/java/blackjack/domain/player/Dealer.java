package blackjack.domain.player;

public class Dealer extends Player {

    private static final int AVAILABLE_POINT_FOR_ADD_CARD = 16;
    private static final String NAME = "딜러";

    private Dealer() {
        super(Name.of(NAME));
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isOneMoreCard() {
        return getPoint() <= AVAILABLE_POINT_FOR_ADD_CARD;
    }
}
