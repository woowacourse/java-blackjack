package blackjack.domain.participant;

public class Betting {

    private static final double WIN_RATE = 2;
    private static final double WIN_BLACKJACK_RATE = 2.5;
    private final Double betting;

    public Betting(Double betting) {
        validateMinBet(betting);
        this.betting = new Double(betting);
    }

    private void validateMinBet(Double betting) {
        if (betting < 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 많아야 합니다.");
        }
    }

    public double getBetting() {
        return this.betting;
    }
}
