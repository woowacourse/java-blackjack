package domain.gamer;

import domain.card.Deck;

public class Dealer extends Gamer {
    private static final int DRAW_CARD_PIVOT = 16;

    public Dealer() {
        super("딜러" );
    }

    public boolean isDrawable() {
        return super.calculateWithAce() <= DRAW_CARD_PIVOT;
    }

    public void addCardAtDealer(Deck deck) {
        while (isDrawable()) {
            addCard(deck.popCard(1));
        }
    }
}
