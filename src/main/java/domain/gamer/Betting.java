package domain.gamer;

public class Betting {

    private final int betAmount;

    public Betting(final int betAmount) {
        validateMaxAndMinAmount(betAmount);
        this.betAmount = betAmount;
    }

    private void validateMaxAndMinAmount(final int betAmount) {
        if (betAmount < 1000 || betAmount > 100000) {
            throw new IllegalArgumentException("배팅 금액은 1,000원 이상, 100,000원 이하여야 합니다.");
        }
    }
}
