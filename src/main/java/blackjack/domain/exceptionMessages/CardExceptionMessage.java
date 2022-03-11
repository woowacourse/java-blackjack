package blackjack.domain.exceptionMessages;

public enum CardExceptionMessage {
	EMPTY_DECK_EXCEPTION("덱의 카드가 다 소진되었습니다.");

	private String message;

	CardExceptionMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
