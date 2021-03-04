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

    public int initializeDraw(Deck deck, int index) {
        draw(deck, index++);
        return draw(deck, index);
    }

    public Cards cards() {
        return cards;
    }

    public boolean isBlackjack(){
        return cards.size() == 2 && cards.getScore() == 21;
    }

    public int getScore() {
        return cards.getScore();
    }
}
