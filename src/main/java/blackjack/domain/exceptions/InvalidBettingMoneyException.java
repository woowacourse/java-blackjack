package blackjack.domain.exceptions;

public class InvalidBettingMoneyException extends IllegalArgumentException {
	public static final String NULL = "배팅 금액이 존재하지 않습니다.";
	public static final String INVALID = "배팅 금액은 양수만 입력 가능합니다.";
	public static final String NOT_INTEGER = "배팅 금액은 숫자만 입력 가능합니다.";

	public InvalidBettingMoneyException(String s) {
		super(s);
	}
}
