package blackjack.domain.rule;

public enum MoneyRule {
    WIN_BLACK_JACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double multiplyValue;

    MoneyRule(double multiplyValue) {
        this.multiplyValue = multiplyValue;
    }

    public double getMultiplyValue() {
        return multiplyValue;
    }
}
