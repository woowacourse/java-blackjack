package blackjack.domain.exceptions;

public class InvalidResultScoreException extends IllegalArgumentException {
	public static final String SCORE_OR_SCORE_TYPE_NULL = "점수 또는 점수 타입이 존재하지 않습니다.";
	public static final String CARDS_EMPTY = "점수를 계산할 카드가 존재하지 않습니다.";

	public InvalidResultScoreException(String s) {
		super(s);
	}
}
