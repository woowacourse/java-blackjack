package blackjack.domain.user;

public class Dealer extends User {
    public static final String NAME = "딜러";
    public static final int MINIMUM_NUMBER_TO_STAY = 17;

    private Dealer() {
        super(NAME, Cards.emptyCards());
    }

    public static Dealer create() {
        return new Dealer();
    }

    public boolean shouldDrawCard() {
        return calculateScore().isUnder(MINIMUM_NUMBER_TO_STAY);
    }
}
