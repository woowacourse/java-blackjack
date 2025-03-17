package domain;

import domain.participant.Participant;

public enum GameResult {
    WIN, DRAW, LOSE;

    public static GameResult calculateDealerResult(int dealerValue, int playerValue) {
        if (isBurstBy(dealerValue) || isBurstBy(playerValue)) {
            return calculateBurstResult(dealerValue, playerValue);
        }
        return calculateResult(dealerValue, playerValue);
    }

    private static GameResult calculateBurstResult(int dealerValue, int playerValue) {
        if (isBurstBy(dealerValue) && isBurstBy(playerValue)) {
            return DRAW;
        }
        if (isBurstBy(playerValue)) {
            return WIN;
        }
        return LOSE;
    }

    private static GameResult calculateResult(int dealerValue, int playerValue) {
        if (dealerValue > playerValue) {
            return WIN;
        }
        if (dealerValue < playerValue) {
            return LOSE;
        }
        return DRAW;
    }

    public static boolean isBurstBy(int value) {
        return value > Participant.BURST_UPPER_BOUND;
    }

    public GameResult reverse() {
        if (this == DRAW) {
            return DRAW;
        }
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }
}
