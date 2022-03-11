package blackjack.domain.exceptionMessages;

public enum UserExceptionMessage {
	EMPTY_NAME_EXCEPTION("이름이 빈 입력이면 안됩니다."),
	BLANK_NAME_EXCEPTION("이름에 공백이 있으면 안됩니다."),
	DUPLICATE_PLAYER_EXCEPTION("이름에 중복이 있으면 안됩니다."),
	EMPTY_PLAYER_EXCEPTION("플레이어를 한명 이상 입력해야 합니다."),
	SCORE_RANGE_EXCEPTION("-1부터 32사이의 숫자만 생성 가능합니다.");

	private String message;

	UserExceptionMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
