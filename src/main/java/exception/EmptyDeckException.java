package exception;

public class EmptyDeckException extends RuntimeException {
	private static final String EMPTY_DECK_MESSAGE = "사용할 수 있는 덱을 모두 소진하였습니다.";
	public EmptyDeckException() {
		super(EMPTY_DECK_MESSAGE);
	}
}
