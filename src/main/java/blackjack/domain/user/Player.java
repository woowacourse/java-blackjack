package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public class Player extends User {

    public Player(String name, double bettingMoney, List<Card> cards, int stayLimit) {
        super(name, bettingMoney, cards, stayLimit);
    }

    @Override
    public boolean draw(Deck deck) {
        hand.addCard(deck.pickSingleCard());
        return true;
    }
}

