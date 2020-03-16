package domain.player;

import domain.card.Card;
import domain.card.CardCalculator;

public class Dealer extends User {
    private static final int ADDITIONAL_INSERT_CARD_STANDARD = 16;

    public Dealer(Card... cards) {
        super(cards);
        this.name = "딜러";
    }

    public boolean isAdditionalCard(Card card) {
        if (CardCalculator.isUnderSixteen(this.cards)) {
            drawCard(card);
            return true;
        }
        return false;
    }

    @Override
    public void drawCard(Card cards) {
        if (sumCardNumber() <= ADDITIONAL_INSERT_CARD_STANDARD) {
            this.cards.add(cards);
        }
        validateDuplicateCard();
    }
}
