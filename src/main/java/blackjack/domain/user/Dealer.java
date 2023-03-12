package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {

    public static final int MINIMUM_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public void updateCardScore(Card card)  {
        handCards.updateCardScore(card);
    }

    public boolean hasUnderMinimumScore() {
        return handCards.getTotalScore() <= MINIMUM_SCORE;
    }
}
