package domain.gamer;

public class Name {
	public static final String LENGTH_ERROR_MESSAGE = "이름의 길이는 1 이상 5 이하이어야 합니다.";

	private final String value;

	public Name(String value) {
		// TODO : 메소드 분리 및 매직넘버 상수화, 예외 메시지
		if (value.length() < 1 || value.length() > 5) {
			throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
		}

		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
