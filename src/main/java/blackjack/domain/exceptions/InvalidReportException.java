package blackjack.domain.exceptions;

public class InvalidReportException extends IllegalArgumentException {
	public static final String EMPTY = "게임 결과를 판단하기 위한 블랙잭 테이블 또는 배팅 금액이 존재하지 않습니다.";

	public InvalidReportException(String s) {
		super(s);
	}
}
