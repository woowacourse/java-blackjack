package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int DEALER_HIT_LIMIT = 16;

    public Dealer(List<Card> cards) {
        super(NAME, cards);
    }

    @Override
    public boolean draw(Deck deck) {
        if (getScore() <= DEALER_HIT_LIMIT) {
            hand.addCard(deck.pickSingleCard());
            return true;
        }
        convertToStay();
        return false;
    }
}
