package domain.player;

import domain.card.CardCalculator;
import domain.card.Card;

public class Dealer extends User {
    private static final int ADDITIONAL_INSERT_CARD_STANDARD = 16;

    public Dealer(Card... cards) {
        super(cards);
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
