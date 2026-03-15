package domain;

public enum HandState {
    NORMAL {
        @Override
        Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            if (playerScore > dealerScore) {
                return Outcome.WIN;
            }

            if (playerScore < dealerScore) {
                return Outcome.LOSE;
            }

            return Outcome.DRAW;
        }
    },
    BLACKJACK {
        @Override
        Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            if (dealerState == BLACKJACK) {
                return Outcome.DRAW;
            }

            return Outcome.WIN;
        }
    },
    BUST {
        @Override
        Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            return Outcome.LOSE;
        }
    };

    private static final int BLACKJACK_SCORE = 21;

    abstract Outcome against(HandState state, int playerScore, int dealerScore);

    public static HandState getState(final int score, final boolean isInitialCards) {
        if (score == BLACKJACK_SCORE) {
            if (isInitialCards) {
                return HandState.BLACKJACK;
            }
            return HandState.NORMAL;
        }

        if (score < 21) {
            return NORMAL;
        }

        return BUST;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isNormal() {
        return this == NORMAL;
    }
}
