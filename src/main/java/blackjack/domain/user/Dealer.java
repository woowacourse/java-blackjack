package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";


    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isMustHit() {
        return cards.totalScore()
                .isDealerMustToHitScore();
    }

    public Card showOneCard() {
        return cards.getOneCard();
    }
}
