package exception;

public class AnswerFormatException extends RuntimeException {
	public AnswerFormatException() {
		super("y 또는 n을 입력해주세요.");
	}
}
