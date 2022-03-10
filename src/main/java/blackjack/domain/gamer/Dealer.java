package blackjack.domain.gamer;

public class Dealer extends Gamer{

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean isOverThan(int number) {
        return getCardsNumberSum() > number;
    }
}
