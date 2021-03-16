package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card showOneCard() {
        return this.state.getCards().oneCard();
    }

    @Override
    public boolean isHit() {
        return this.cards.calculateScore().isDealerHit();
    }
}
