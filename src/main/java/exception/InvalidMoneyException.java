package exception;

public class InvalidMoneyException extends RuntimeException {
	public InvalidMoneyException(String money) {
		super(String.format("%s 는 허용된 범위의 금액이 아닙니다.", money));
	}
}
