package blackjack.domain.result;

public class InvalidReportException extends IllegalArgumentException {
	public static final String EMPTY = "게임 결과를 판단하기 위한 딜러 또는 플레이어가 존재하지 않습니다.";

	public InvalidReportException(String s) {
		super(s);
	}
}
