package domain;

public class Bet {

    private static final int UNIT_OF_BET = 1000;
    private final int amount;

    public Bet(String amount) {
        this.amount = validate(amount);
    }

    public int getBetAmount() {
        return amount;
    }

    private int validate(String amount) {
        int parsedAmount = parseAmount(amount);
        validateUnit(parsedAmount);

        return parsedAmount;
    }

    private void validateUnit(int parsedAmount) {
        if (parsedAmount % UNIT_OF_BET != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1000원 단위로만 입력 가능합니다.");
        }
    }

    private int parseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자만 입력 가능합니다.");
        }
    }
}
