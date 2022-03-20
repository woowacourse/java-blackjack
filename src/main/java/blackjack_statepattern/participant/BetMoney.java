package blackjack_statepattern.participant;

public class BetMoney {

    private final int amount;

    public BetMoney(String inputValue) {
        validateBetMoney(inputValue);
        this.amount = Integer.parseInt(inputValue);
    }

    private void validateBetMoney(String inputValue) {
        int amount = checkInteger(inputValue);
        checkNotNegative(amount);
    }

    private void checkNotNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 음수가 될 수 없습니다.");
        }
    }

    private int checkInteger(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 숫자로 입력해주세요.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
