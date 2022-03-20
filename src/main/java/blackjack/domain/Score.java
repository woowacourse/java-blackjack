package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;

public enum Score {
    BLACKJACK_WIN(1.5) {
        @Override
        public boolean match(Participant participant, Dealer dealer) {
            return participant.isBlackjack() && !dealer.isBlackjack();
        }
    },
    WIN(1) {
        @Override
        public boolean match(Participant participant, Dealer dealer) {
            if (participant.isBlackjack() && dealer.isBlackjack()) {
                return true;
            }
            if (participant.isBust()) {
                return false;
            }
            if (!dealer.isBust()) {
                return participant.getTotalNumber() > dealer.getTotalNumber();
            }
            return true;

        }
    },
    LOSE(-1) {
        @Override
        public boolean match(Participant participant, Dealer dealer) {
            if (participant.isBust()) {
                return true;
            }
            if (!participant.isBlackjack() && !dealer.isBust()) {
                return participant.getTotalNumber() <= dealer.getTotalNumber();
            }
            return false;
        }
    };

    private final double dividendRate;

    Score(double dividendRate) {
        this.dividendRate = dividendRate;
    }

    abstract public boolean match(Participant participant, Dealer dealer);

    public double getDividendRate() {
        return dividendRate;
    }
}
