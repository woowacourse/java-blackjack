package blakcjack.domain.name;

import java.util.Objects;

public class Name {
	private final String name;

	public Name(final String name) {
		validateNullOrEmpty(name);
		this.name = name;
	}

	private void validateNullOrEmpty(final String name) {
		if (Objects.isNull(name)) {
			throw new IllegalPlayerNameException();
		}
		if (name.isEmpty()) {
			throw new IllegalPlayerNameException();
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
