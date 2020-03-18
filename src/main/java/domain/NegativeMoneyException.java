package domain;

public class NegativeMoneyException extends IllegalArgumentException {
    public NegativeMoneyException() {
        super("Money는 음수일 수 없습니다.");
    }
}
