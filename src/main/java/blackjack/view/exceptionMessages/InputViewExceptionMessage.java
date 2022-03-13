package blackjack.view.exceptionMessages;

public enum InputViewExceptionMessage {
	NULL_STRING_EXCEPTION("문자열의 입력이 NULL 이면 안됩니다."),
	DECISION_INPUT_EXCEPTION("입력은 Y,N(소문자 가능)만 입력해주세요.");

	private String message;

	InputViewExceptionMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
