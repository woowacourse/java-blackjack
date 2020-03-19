package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private Deck deck;

    public Dealer(PlayingCards playingCards, Deck deck, Money money) {
        super(playingCards, NAME, money);
        this.deck = deck;
    }

    void confirmCards(int hitSize) {
        for (int i = 0; i < hitSize; i++) {
            Card card = deck.pop();
            hit(card);
        }
    }


}
