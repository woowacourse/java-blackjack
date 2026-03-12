package model.game.exception;

public class BettingUnitMismatchException extends IllegalArgumentException {
    public BettingUnitMismatchException(int userAmount, int divideUnit) {
        super("베팅 금액은 " + divideUnit + "원 단위여야 합니다. (현재 입력값: " + userAmount + ")");
    }
}
