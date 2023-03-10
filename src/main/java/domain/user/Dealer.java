package domain.user;

import domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;
    private static final int NUMBERS_OF_CARDS_TO_HIDE = 1;
    private static final int DEPOSIT = 100_000;

    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final CardPool cardPool) {
        super(DEFAULT_NAME, cardPool, DEPOSIT);
    }

    public boolean needsHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }

    public CardPool getHiddenCardPool() {
        List<Card> originalCards = super.getCardPool().getCards();
        return new CardPool(originalCards.subList(0, originalCards.size() - NUMBERS_OF_CARDS_TO_HIDE));
    }
}
