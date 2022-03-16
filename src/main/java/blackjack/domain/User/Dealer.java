package blackjack.domain.User;

import blackjack.domain.Card.CardFactory;
import blackjack.domain.Card.Cards;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    public boolean canOneMoreCard() {
        return cards.getScore() <= DEALER_ADD_CARD_LIMIT;
    }

    @Override
    public boolean hit(CardFactory cardFactory) {
        if (canOneMoreCard()) {
            cards.add(cardFactory.drawOneCard());
            return true;
        }
        return false;
    }

}
