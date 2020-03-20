package blackjack.domain.exceptions;

public class InvalidUserDecisionsException extends IllegalArgumentException {
	public static final String USER_DECISIONS_NULL = "choice 또는 handStatus 또는 dealerChoice가 입력되지 않았습니다.";
	public static final String PLAYER_NULL = "플레이어가 존재하지 않습니다.";

	public InvalidUserDecisionsException(String s) {
		super(s);
	}
}
