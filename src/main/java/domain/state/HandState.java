package domain.state;

public enum HandState {
    STAY {
        @Override
        public Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            if(dealerState == BLACKJACK){
                return Outcome.LOSE;
            }

            if (dealerState.isBust()) {
                return Outcome.DEFAULT_WIN;
            }

            if (playerScore > dealerScore) {
                return Outcome.DEFAULT_WIN;
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

            return Outcome.BLACKJACK_WIN;
        }
    },
    BUST {
        @Override
        public Outcome against(HandState dealerState, int playerScore, int dealerScore) {
            return Outcome.LOSE;
        }
    };

    public abstract Outcome against(HandState state, int playerScore, int dealerScore);

    public boolean isBust() {
        return this == BUST;
    }
}
