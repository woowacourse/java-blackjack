package domain.player;

public class Gambler extends Participant{

    private final Long betAmount;

    public Gambler(String name, Long betAmount) {
        super(name);
        validateBetAmount(betAmount);
    }

    private void validateBetAmount(Long betAmount) {
        validateBetAmountIsPositive(betAmount);
        validateAmountDoesNotExceedMaxAmount(betAmount)
    }

    private void validateBetAmountIsPositive(Long betAmount) {
        if (betAmount > 0) {

        }
    }

    private void validateAmountDoesNotExceedMaxAmount(Long betAmount) {
        if (betAmount > 10_000_000_000L) {
            throw new IllegalArgumentException("베팅 금액은 100억을 넘지 않는다");
        }
    }
}
