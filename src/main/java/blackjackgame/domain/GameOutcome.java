package blackjackgame.domain;

public enum GameOutcome implements RevenueCalculator {
    BLACKJACK_WIN("승") {
        public int calculate(final int money) {
            return (int) (money * 1.5);
        }
    },
    WIN("승") {
        public int calculate(final int money) {
            return money;
        }
    },
    LOSE("패") {
        public int calculate(final int money) {
            return money * -1;
        }
    },
    DRAW("무") {
        public int calculate(final int money) {
            return 0;
        }
    };

    private final String outcome;

    GameOutcome(final String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }
}
