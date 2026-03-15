package domain;

public enum State {
    NORMAL {
        @Override
        Outcome against(State dealerState, int playerScore, int dealerScore) {
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
        Outcome against(State dealerState, int playerScore, int dealerScore) {
            if (dealerState == BLACKJACK) {
                return Outcome.DRAW;
            }

            return Outcome.WIN;
        }
    },
    BUST {
        @Override
        Outcome against(State dealerState, int playerScore, int dealerScore) {
            return Outcome.LOSE;
        }
    };

    abstract Outcome against(State state, int playerScore, int dealerScore);

    public static State getState(int score, int cardCount) {
        if (score == 21) {
            if (cardCount == 2) {
                return State.BLACKJACK;
            }
            return State.NORMAL;
        }

        if (score < 21) {
            return NORMAL;
        }

        return BUST;
    }
}
