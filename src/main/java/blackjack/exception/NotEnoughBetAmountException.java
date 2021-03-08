package blackjack.exception;

public class NotEnoughBetAmountException extends RuntimeException {

    private static final String MESSAGE = "[ERROR] 최소 배팅 금액이 부족합니다.";

    public NotEnoughBetAmountException() {
        super(MESSAGE);
    }
}
