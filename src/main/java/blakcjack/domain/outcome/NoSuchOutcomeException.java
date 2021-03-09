package blakcjack.domain.outcome;

public class NoSuchOutcomeException extends RuntimeException {
	private final static String NO_SUCH_OUTCOME_ERROR = "일치하는 결과가 존재하지 않습니다.";

	public NoSuchOutcomeException() {
		super(NO_SUCH_OUTCOME_ERROR);
	}
}
