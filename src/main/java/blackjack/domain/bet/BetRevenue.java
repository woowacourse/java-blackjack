package blackjack.domain.bet;

public record BetRevenue(double value) {

    public BetRevenue plus(final BetRevenue other) {
        return new BetRevenue(value + other.value);
    }

    public BetRevenue negate() {
        return new BetRevenue(-value);
    }
}
