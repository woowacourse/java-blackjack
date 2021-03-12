package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isMustHit() {
        boolean isMustHit = state.cards()
                .totalScore()
                .isDealerMustToHitScore();
        if (isMustHit) {
            return true;
        }
        if (isAbleToHit()) {
            stay();
        }
        return false;
    }

    public Card showOneCard() {
        return state.cards()
                .getOneCard();
    }
}
