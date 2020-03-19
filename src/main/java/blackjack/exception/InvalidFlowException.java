package blackjack.exception;

public class InvalidFlowException extends RuntimeException {
	public InvalidFlowException() {
		super("잘못된 함수 호출입니다.");
	}
}
