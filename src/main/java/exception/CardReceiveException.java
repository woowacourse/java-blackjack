package exception;

public class CardReceiveException extends IllegalArgumentException {
    public static final String CAN_NOT_RECEIVE_CARD = "더 이상 카드를 받을 수 없습니다.";

    public CardReceiveException(final String message) {
        super(message);
    }
}
