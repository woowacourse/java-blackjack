package blackjack.domain.user;

public class Dealer extends User {
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
