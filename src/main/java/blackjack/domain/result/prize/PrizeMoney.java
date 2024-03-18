package blackjack.domain.result.prize;

public record PrizeMoney(int value) {
    public PrizeMoney(final double value) {
        this((int) value);
    }
}
