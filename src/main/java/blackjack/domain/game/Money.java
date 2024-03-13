package blackjack.domain.game;

public record Money(int value) {

    public Money multipleRatio(double ratio) {
        return new Money((int) (this.value * ratio));
    }
}
