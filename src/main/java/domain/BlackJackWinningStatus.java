package domain;

import domain.participant.Participant;

public enum BlackJackWinningStatus {
    BLACK_JACK_WIN,
    WIN,
    DRAW,
    BLACK_JACK_LOSE,
    LOSE;

    public static BlackJackWinningStatus calculateDealerResult(boolean isDealerBlackJack, int dealerValue,
                                                               boolean isPlayerBlackJack, int playerValue) {
        if (isDealerBlackJack || isPlayerBlackJack) {
            return calculateBlackjackResult(isDealerBlackJack, isPlayerBlackJack);
        }
        if (isBurstBy(dealerValue) || isBurstBy(playerValue)) {
            return calculateBurstResult(dealerValue, playerValue);
        }
        return calculateResult(dealerValue, playerValue);
    }

    private static BlackJackWinningStatus calculateBlackjackResult(boolean isDealerBlackJack,
                                                                   boolean isPlayerBlackJack) {
        if (isDealerBlackJack && isPlayerBlackJack) {
            return DRAW;
        }
        if (isDealerBlackJack) {
            return BLACK_JACK_WIN;
        }
        return BLACK_JACK_LOSE;
    }

    private static BlackJackWinningStatus calculateBurstResult(int dealerValue, int playerValue) {
        if (isBurstBy(dealerValue) && isBurstBy(playerValue)) {
            return WIN;
        }
        if (isBurstBy(playerValue)) {
            return WIN;
        }
        return LOSE;
    }

    private static BlackJackWinningStatus calculateResult(int dealerValue, int playerValue) {
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

    public BlackJackWinningStatus reverse() {
        if (this == BLACK_JACK_WIN) {
            return BLACK_JACK_LOSE;
        }
        if (this == BLACK_JACK_LOSE) {
            return BLACK_JACK_WIN;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
