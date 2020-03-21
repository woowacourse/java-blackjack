package exception;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException(Object money) {
		super("잘못된 돈입니다." + money);
	}
}
