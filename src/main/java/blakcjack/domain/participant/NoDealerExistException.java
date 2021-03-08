package blakcjack.domain.participant;

public class NoDealerExistException extends RuntimeException {
	private final static String NO_DEALER_ERROR = "딜러가 존재하지 않습니다.";

	public NoDealerExistException() {
		super(NO_DEALER_ERROR);
	}
}
