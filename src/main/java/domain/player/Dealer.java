package domain.player;

import domain.CardCalculator;
import domain.card.Card;

import java.util.List;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int INITIAL_CARDS_SIZE = 2;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public int getHitCardsCount() {
        return this.cards.getCardsSize() - INITIAL_CARDS_SIZE;
    }

    @Override
    public boolean isHit() {
        return CardCalculator.isUnderDealerStandard(this.cards);
    }
}
