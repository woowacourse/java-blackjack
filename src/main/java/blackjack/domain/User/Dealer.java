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
    public void hit(CardFactory cardFactory) {
        while (canOneMoreCard()) {
            cards.add(cardFactory.drawOneCard());
        }
    }

}
