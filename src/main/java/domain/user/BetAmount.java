package domain.user;

public record BetAmount(int value) {
    public BetAmount times(double multiplier) {
        return new BetAmount((int) (this.value * multiplier));
    }
}
