package domain;

public class NegativeMoneyException extends IllegalArgumentException {
    public NegativeMoneyException(int input) {
        super(String.format("Money는 음수일 수 없습니다. (입력 : %d)", input));
    }
}
