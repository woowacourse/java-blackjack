package exception;

public class MissResultException extends RuntimeException {
	public MissResultException() {
		super("결과가 존재 하지 않습니다.");
	}
}
