package blackjack.domain.User;

import blackjack.domain.Card.Cards;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isHit() {
        return cards.getScore() <= DEALER_ADD_CARD_LIMIT;
    }

}
