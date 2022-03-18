package blackjack.domain.participant;

import blackjack.domain.prizecalculator.BlackjackCalculator;
import blackjack.domain.prizecalculator.BustCalculator;
import blackjack.domain.prizecalculator.PrizeCalculator;
import blackjack.domain.prizecalculator.StayCalculator;

public enum PlayerStatus {

    HIT(true) {
        @Override
        public PrizeCalculator findCalculator() {
            throw new IllegalStateException();
        }
    },
    BUST(false) {
        @Override
        public PrizeCalculator findCalculator() {
            return BustCalculator.getInstance();
        }
    },
    STAY(false) {
        @Override
        public PrizeCalculator findCalculator() {
            return StayCalculator.getInstance();
        }
    },
    BLACKJACK(false) {
        @Override
        public PrizeCalculator findCalculator() {
            return BlackjackCalculator.getInstance();
        }
    },
    ;

    private final boolean isRunning;

    PlayerStatus(final boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public abstract PrizeCalculator findCalculator();
}
