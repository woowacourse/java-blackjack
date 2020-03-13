package domain.user;

import java.util.Objects;

public class Name {
	private static final String NULL_OR_EMPTY_NAME_EXCEPTION_MESSAGE = "이름에 빈값이 들어갈 수 없습니다.";

	private final String name;

	public Name(String name) {
		validEmptyAndNull(name);
		this.name = name;
	}

	private void validEmptyAndNull(String name) {
		if (Objects.isNull(name) || name.isEmpty()) {
			throw new IllegalArgumentException(NULL_OR_EMPTY_NAME_EXCEPTION_MESSAGE);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Name that = (Name)object;
		return Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
