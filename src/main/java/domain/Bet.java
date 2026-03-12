package domain;

public class Bet {

    private static final int UNIT_OF_BET = 1000;
    private static final int MINIMUM_BET_AMOUNT = 1000;

    private final int amount;

    public Bet(String amount) {
        this.amount = validate(amount);
    }

    public int getBetAmount() {
        return amount;
    }

    private int validate(String amount) {
        int parsedAmount = parseAmount(amount.trim());
        validateUnit(parsedAmount);
        validateMinimum(parsedAmount);

        return parsedAmount;
    }

    private int parseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자만 입력 가능합니다.");
        }
    }

    private void validateUnit(int parsedAmount) {
        if (parsedAmount % UNIT_OF_BET != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1000원 단위로만 입력 가능합니다.");
        }
    }

    private void validateMinimum(int parsedAmount) {
        if (parsedAmount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 최소 베팅 금액은 1000원입니다.");
        }
    }
}
