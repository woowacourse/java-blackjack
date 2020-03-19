package blackjack.domain.user;

import blackjack.domain.user.exceptions.NameException;

import java.util.Objects;

public final class Name {
	final String name;

	public Name(String name) {
		validateNameIsNotNull(name);
		validateNameIsNotBlank(name);
		this.name = name;
	}

	private void validateNameIsNotNull(String name) {
		if (name == null) {
			throw new NameException("이름을 Null일 수 없습니다.");
		}
	}

	private void validateNameIsNotBlank(String name) {
		if (name.trim().isEmpty()) {
			throw new NameException("이름은 공백일 수 없습니다.");
		}
	}

	public String getString() {
		return name;
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

	@Override
	public String toString() {
		return "Name{" +
				"name='" + name + '\'' +
				'}';
	}
}
