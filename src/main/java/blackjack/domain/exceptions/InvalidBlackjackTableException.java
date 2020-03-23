package blackjack.domain.exceptions;

public class InvalidBlackjackTableException extends IllegalArgumentException {
	public static final String DECK_OR_DEALER_NULL = "덱 또는 딜러가 존재하지 않습니다.";
	public static final String PLAYERS_EMPTY = "플레이어들이 존재하지 않습니다.";
	public static final String USER_DECISIONS_NULL = "유저의 입력을 위한 기능이 존재하지 않습니다.";
	public static final String PLAYERS_BETTING_MONEY_EMPTY = "플레이어들의 배팅 머니가 존재하지 않습니다.";

	public InvalidBlackjackTableException(String s) {
		super(s);
	}
}
