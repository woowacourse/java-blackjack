package blackjack.user;

import blackjack.card.Score;

public class Dealer extends AbstractUser {
    private static final String NAME = "딜러";


    private Dealer() {
        super(NAME);
    }

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public Boolean isWinner(Score dealerScore) {
        return true;
    }
}
