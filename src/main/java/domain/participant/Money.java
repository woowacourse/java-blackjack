package domain.participant;

public class Money {
    private static final String MONEY_REGEX_FORMAT = "^[0-9]+$";
    public static final String BETTING_AMOUNT_ZERO_MESSAGE = "[ERROR] 베팅 금액은 0이 될 수 없습니다.";

    private final int amount;

    public Money(String amount) {
        validateAmountRegex(amount);
        this.amount = validateAmount(Integer.parseInt(amount));
    }

    public Money(int amount) {
        this.amount = validateAmount(amount);
    }

    public Money() {
        this.amount = 0;
    }

    private void validateAmountRegex(String amount) {
        if (!amount.matches(MONEY_REGEX_FORMAT)) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자로 입력해 주세요");
        }
    }

    private int validateAmount(int number) {
        if (number == 0) {
            throw new IllegalArgumentException(BETTING_AMOUNT_ZERO_MESSAGE);
        }
        return number;
    }

    public int getAmount() {
        return amount;
    }
}
