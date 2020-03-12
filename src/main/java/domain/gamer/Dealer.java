package domain.gamer;

import domain.card.Deck;
import view.OutputView;

public class Dealer extends Gamer {
    private static final int DRAW_CARD_PIVOT = 16;

    public Dealer() {
        super("딜러" );
    }

    public boolean isDrawable() {
        return super.calculateWithAce() <= DRAW_CARD_PIVOT;
    }

    public String initCardToString() {
        return super.cards.get(0).toString();
    }

    public void addCardAtDealer(Deck deck) {
        while (isDrawable()) {
            addCard(deck.popCard(1));
        }
    }
}
