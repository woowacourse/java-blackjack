package blakcjack.domain.name;

import java.util.Objects;

import static blakcjack.domain.name.IllegalPlayerNameException.ILLEGAL_NAME_ERROR;

public class Name {
	private final String name;

	public Name(final String name) {
		validateNullOrEmpty(name);
		this.name = name;
	}

	private void validateNullOrEmpty(final String name) {
		if (Objects.isNull(name)) {
			throw new IllegalPlayerNameException(ILLEGAL_NAME_ERROR);
		}
		if (name.isEmpty()) {
			throw new IllegalPlayerNameException(ILLEGAL_NAME_ERROR);
		}
	}

	public String getName() {
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
}
