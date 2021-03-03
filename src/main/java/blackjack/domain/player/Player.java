package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public class Player {

    private Cards cards;

    public Player() {
        this.cards = new Cards();
    }

    public int draw(Deck deck, int index) {
        cards.add(deck.getCard(index++));
        return index;
    }

    public Cards cards() {
        return cards;
    }
}
