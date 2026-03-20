package exception;

public class BettingAmountOutOfRangeException extends BlackjackException{
    public BettingAmountOutOfRangeException(int min, int max) {
        super(String.format("베팅 금액은 %d원 ~ %d원 사이여야 합니다.", min,  max));
    }
}
