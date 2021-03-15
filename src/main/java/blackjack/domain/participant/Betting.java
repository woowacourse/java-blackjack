package blackjack.domain.participant;

import java.util.Objects;

public class Betting {

    private final Double betting;

    public Betting(Double betting) {
        validateNull(betting);
        validateMinBet(betting);
        this.betting = betting;
    }

    private void validateNull(Double betting) {
        Objects.requireNonNull(betting, "Null은 허용하지 않습니다.");
    }

    private void validateMinBet(Double betting) {
        if (betting < 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 이상이어야 합니다.");
        }
    }

    public double getBetting() {
        return this.betting;
    }
}
