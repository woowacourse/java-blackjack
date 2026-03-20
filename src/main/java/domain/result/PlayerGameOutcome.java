package domain.result;

import domain.Money;

enum PlayerGameOutcome {

    BLACK_JACK_WIN {
        Money calculate(Money betting) {
            return ProfitRate.BLACK_JACK_WIN.calculateProfit(betting);
        }
    },

    WIN {
        Money calculate(Money betting) {
            return ProfitRate.WIN.calculateProfit(betting);
        }
    },

    DRAW {
        Money calculate(Money betting) {
            return ProfitRate.DRAW.calculateProfit(betting);
        }
    },

    LOSE {
        Money calculate(Money betting) {
            return ProfitRate.LOSE.calculateProfit(betting);
        }
    },
    ;

    abstract Money calculate(Money betting);

    private enum ProfitRate {
        BLACK_JACK_WIN(1.5),
        WIN(1),
        DRAW(0),
        LOSE(-1),
        ;

        private final double rate;

        ProfitRate(double rate) {
            this.rate = rate;
        }

        private Money calculateProfit(Money money) {
            long rounded = Math.round(money.amount() * rate);
            return Money.of(rounded);
        }
    }
}
