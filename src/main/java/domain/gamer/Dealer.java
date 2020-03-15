package domain.gamer;

import domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {
    private static final int DRAW_CARD_PIVOT = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDrawable() {
        return result.getScore() <= DRAW_CARD_PIVOT;
    }

    public void addCardAtDealer(List<Card> card) {
        while (isDrawable()) {
            addCard(card);
        }
    }
}
