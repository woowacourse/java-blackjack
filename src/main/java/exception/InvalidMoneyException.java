package exception;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException(Object money) {
		super(String.format("잘못된 돈입니다. %s", money));
	}
}
