package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isMustHit() {
        return state.cards()
                .totalScore()
                .isDealerMustToHitScore();
    }

    public Card showOneCard() {
        return state.cards()
                .getOneCard();
    }
}
