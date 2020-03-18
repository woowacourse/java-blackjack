package blackjack.domain.exceptions;

public class InvalidPlayerException extends IllegalArgumentException {
	public static final String DUPLICATE_PLAYER = "중복된 플레이어가 존재합니다.";
	public static final String DUPLICATE_DEALER = "딜러와 이름이 중복된 플레이어가 존재합니다.";
	public static final String SIZE = "플레이 가능한 최대 인원(7명)을 초과하였습니다.";

	public InvalidPlayerException(String s) {
		super(s);
	}
}
