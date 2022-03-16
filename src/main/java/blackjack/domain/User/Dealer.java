package blackjack.domain.User;

import blackjack.domain.Card.Deck;
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
    public void hit(Deck deck) {
        while (canOneMoreCard()) {
            cards.add(deck.drawOneCard());
        }
    }

}
