package blackjack.domain.gamer;

public class Money {
    private static final int MIN = 10;
    private static final int DIVIDED = 10;
    private static final String MIN_ERROR_MESSAGE = MIN + "원 이상의 돈을 입력해주세요.";
    private static final String UNIT_ERROR_MESSAGE = DIVIDED + "원 단위의 금액을 입력해주세요.";

    private int amount = 0;

    public void add(int amount) {
        validate(amount);
        this.amount += amount;
    }

    private void validate(int amount) {
        validateMin(amount);
        validateDivisible(amount);
    }

    private void validateMin(int amount) {
        if (amount < MIN) {
            throw new IllegalArgumentException(MIN_ERROR_MESSAGE);
        }
    }

    private void validateDivisible(int amount) {
        if (amount % DIVIDED != 0) {
            throw new IllegalArgumentException(UNIT_ERROR_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
