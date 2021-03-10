package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public class Dealer extends User {
    private static final String NAME = "딜러";

    public Dealer(List<Card> cards, int stayLimit) {
        super(NAME, cards, stayLimit);
    }

    @Override
    public boolean draw(Deck deck) {
        if (isHit()) {
            hand.addCard(deck.pickSingleCard());
            return true;
        }
        return false;
    }
}
