package blackjack.domain.gamer;

import java.util.Objects;

public class Name {
	private static final String BLANK_NAME = "[ERROR] 이름이 빈 입력이면 안됩니다.";
	private static final String SPACE_IN_NAME = "[ERROR] 이름에 공백이 있으면 안됩니다.";

	private final String name;

	public Name(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(BLANK_NAME);
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException(SPACE_IN_NAME);
		}
		this.name = name;
	}

	public Name() {
		this("");
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Name name1 = (Name) o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
