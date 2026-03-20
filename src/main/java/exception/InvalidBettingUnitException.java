package exception;

public class InvalidBettingUnitException extends BlackjackException{
    public InvalidBettingUnitException(int bettingUnit) {
        super("베팅 금액은 " + bettingUnit + "원 단위여야 합니다.");
    }
}
