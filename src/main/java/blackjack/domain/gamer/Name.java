package blackjack.domain.gamer;

public class Name {
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 5;
	private static final String LENGTH_ERROR_MESSAGE =
		String.format("이름의 길이는 %d 이상 %d 이하이어야 합니다.", MIN_LENGTH, MAX_LENGTH);

	private final String value;

	public Name(String value) {
		validateLength(value);
		this.value = value;
	}

	private void validateLength(String value) {
		if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
			throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
		}
	}

	public String getValue() {
		return value;
	}
}
