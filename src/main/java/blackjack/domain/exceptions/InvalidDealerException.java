package blackjack.domain.exceptions;

public class InvalidDealerException extends IllegalArgumentException {
	public static final String EMPTY = "딜러가 가질 카드들이 존재하지 않습니다.";

	public InvalidDealerException(String s) {
		super(s);
	}
}
