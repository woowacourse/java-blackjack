package blackjack.domain.user;

public class Dealer extends User {
    private static final String NAME = "딜러";

    @Override
    public String getName() {
        return NAME;
    }
}
