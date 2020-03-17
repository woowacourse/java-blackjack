package exception;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException() {
		super("잘못된 돈입니다.");
	}
}
