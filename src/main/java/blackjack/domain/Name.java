package blackjack.domain;

public class Name {

	private final String name;

	public Name(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (name.isBlank()) {
			throw new IllegalArgumentException("빈 분자열이 입력 되었습니다.");
		}
	}
}
