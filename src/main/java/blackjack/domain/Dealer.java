package blackjack.domain;

import java.util.List;

public class Dealer extends User {
    private static final int DEALER_CRITICAL_SCORE = 16;
    private static final String KOREAN_NAME = "딜러";
    private static final int DEALER_INITIAL_CARDS_SIZE = 1;

    private static Dealer dealerInstance;

    private Dealer() {
        this.name = KOREAN_NAME;
    }

    public static Dealer getDealer() {
        if (dealerInstance == null) {
            dealerInstance = new Dealer();
        }
        return dealerInstance;
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.getCards()
                .subList(START_INDEX, DEALER_INITIAL_CARDS_SIZE);
    }

    @Override
    public boolean isReceivableOneMoreCard() {
        return cards.isScoreUnder(DEALER_CRITICAL_SCORE);
    }
}