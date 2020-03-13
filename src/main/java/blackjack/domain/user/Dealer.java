package blackjack.domain.user;

import blackjack.domain.result.WinOrLose;

public class Dealer extends AbstractUser {
    public static final String NAME = "딜러";
    public static final int MINIMUM_NUMBER_TO_STAY = 17;

    private Dealer() {
        super(NAME);
    }

    public static Dealer create() {
        return new Dealer();
    }

    public boolean shouldReceiveCard() {
        return calculateScore().isUnder(MINIMUM_NUMBER_TO_STAY);
    }
}
