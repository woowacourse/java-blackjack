package blackjack.domain.gamer;

import java.util.Objects;

public class Name {
	private static final String BLANK_NAME_ERROR = "이름은 공백이 될 수 없습니다.";
	private final String name;

	public Name(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (name.isBlank()) {
			throw new IllegalArgumentException(BLANK_NAME_ERROR);
		}
	}

	public boolean isSame(String name) {
		return this.name.equals(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Name name1 = (Name) o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public String getValue() {
		return name;
	}
}
