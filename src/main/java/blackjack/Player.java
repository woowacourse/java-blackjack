package blackjack;

import blackjack.cards.Cards;

public class Player {

    private final Cards cards;

    public Player(Card card1, Card card2, Card... cards) {
        this.cards = Cards.mixHandCards(card1, card2, cards);
    }

    public boolean isHittable() {
        return cards.score().lessThan(new Score(21));
    }
}
