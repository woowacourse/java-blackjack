package model.game.exception;

public class UnderMinimumAmountException extends IllegalArgumentException {
    public UnderMinimumAmountException(int userAmount, int minimumAmount) {
        super("최소 베팅 금액은 " + minimumAmount + "원입니다. (현재 입력값: " + userAmount + ")");
    }
}
