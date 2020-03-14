package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {
    private static final int DEALER_CRITICAL_SCORE = 16;
    private static final int DEALER_INITIAL_CARDS_SIZE = 1;
    private static final String KOREAN_NAME = "딜러";

    private static Dealer dealerInstance;

    private Dealer() {
        this.name = KOREAN_NAME;
        this.status = Status.NONE;
    }

    public static Dealer getDealer() {
        if (dealerInstance == null) {
            dealerInstance = new Dealer();
        }
        return dealerInstance;
    }

    public boolean isUnderCriticalScore() {
        return calculateScore() <= DEALER_CRITICAL_SCORE;
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.subList(START_INDEX, DEALER_INITIAL_CARDS_SIZE);
    }
}