package blackjack.domain.gamer;

public record Name(String value) {

	private static final int MAX_NAME_LENGTH = 5;

	public Name {
		if (value.isBlank() || value.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("이름의 길이는 최소 1글자부터 최대 5글자까지 가능합니다.");
		}
	}
}
