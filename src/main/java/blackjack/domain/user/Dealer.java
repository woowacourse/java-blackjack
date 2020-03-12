package blackjack.domain.user;

import blackjack.domain.card.Score;

public class Dealer extends AbstractUser {
    private static final String NAME = "딜러";
    public static final int MINIMUM_NUMBER_TO_STAY = 17;

    private Dealer() {
        super(NAME);
    }

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public Boolean isWinner(Score dealerScore) {
        return isNotBust();
    }

    public boolean shouldReceiveCard() {
        return calculateScore().isUnder(MINIMUM_NUMBER_TO_STAY);
    }
}
