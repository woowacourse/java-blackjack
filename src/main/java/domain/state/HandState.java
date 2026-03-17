package domain.state;

public enum HandState {
    HIT {
        @Override
        public Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            if (dealerState.isBust()) {
                return Outcome.WIN;
            }

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
        public Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            if (dealerState == BLACKJACK) {
                return Outcome.DRAW;
            }

            return Outcome.WIN;
        }
    },
    BUST {
        @Override
        public Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            return Outcome.LOSE;
        }
    };

    private static final int BLACKJACK_SCORE = 21;

    public abstract Outcome against(HandState state, int playerScore, int dealerScore);

    public static HandState getState(final int score, final boolean isInitialCards) {
        if (score == BLACKJACK_SCORE) {
            if (isInitialCards) {
                return HandState.BLACKJACK;
            }
            return HandState.HIT;
        }

        if (score < BLACKJACK_SCORE) {
            return HIT;
        }

        return BUST;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isHit() {
        return this == HIT;
    }
}
