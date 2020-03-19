package domain.player;

import domain.CardCalculator;
import domain.card.Card;

import java.util.List;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public int getCardsSize() {
        return this.cards.getCardsSize();
    }

    @Override
    public boolean isHit() {
        return CardCalculator.isUnderDealerStandard(this.cards);
    }
}
