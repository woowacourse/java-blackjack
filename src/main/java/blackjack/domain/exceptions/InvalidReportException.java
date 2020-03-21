package blackjack.domain.exceptions;

public class InvalidReportException extends IllegalArgumentException {
	public static final String NULL = "플레이어들의 베팅 금액이 존재하지 않습니다.";

	public InvalidReportException(String s) {
		super(s);
	}
}
