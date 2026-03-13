package domain;

import java.util.List;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        List<Card> currentCards = super.getCards();
        return List.of(currentCards.getFirst());
    }

    @Override
    public boolean isDrawable() {
        return super.getCardsSum() <= MINIMUM_TOTAL_SCORE;
    }
}
