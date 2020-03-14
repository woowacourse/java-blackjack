package domain.player;

import domain.CardCalculator;
import domain.card.Card;
import domain.card.Cards;

public class Dealer extends Player {
    private static final String DEALER_NAME = "딜러";
    private static final int STANDARD = 16;

    public Dealer(Card... cards) {
        super(DEALER_NAME, cards);
    }

    public boolean isAdditionalCard(Cards cards) {
        if (CardCalculator.isUnderSixteen(this.cards)) {
            insertCard(cards);
            return true;
        }
        return false;
    }

    @Override
    public void insertCard(Cards cards) {
        if (sumCardNumber() <= STANDARD) {
            this.cards.add(cards.pop());
        }
    }
}
