package blackjack.model;

import blackjack.model.participant.Hand;

public enum FinishedState {

    BLACKJACK,
    BUST,
    STAND;

    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    FinishedState() {
    }

    public static FinishedState checkFinishedState(Hand hand) {
        int score = hand.getScore();
        if (score == BLACKJACK_NUMBER && hand.size() == BLACKJACK_CARD_COUNT) {
            return FinishedState.BLACKJACK;
        }
        if (score > BLACKJACK_NUMBER) {
            return FinishedState.BUST;
        }
        return FinishedState.STAND;
    }
}
