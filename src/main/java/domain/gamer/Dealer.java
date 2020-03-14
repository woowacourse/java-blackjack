package domain.gamer;

import domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {
    private static final int DRAW_CARD_PIVOT = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean isDrawable() {
        return super.calculateWithAce() <= DRAW_CARD_PIVOT;
    }

    public void addCardAtDealer(List<Card> card) {
        while (isDrawable()) {
            addCard(card);
        }
    }
}
