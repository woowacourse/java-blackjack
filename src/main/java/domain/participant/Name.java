package domain.participant;

import java.util.Objects;

public class Name {

	protected static final String BLANK_NAME_ERROR_MESSAGE = "[Error] 이름은 공백이거나 빈칸일 수 없습니다.";

	private final String name;

	public Name(String name) {
		validateName(name);
		this.name = name;
	}

	protected void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(BLANK_NAME_ERROR_MESSAGE);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Name))
			return false;
		Name name1 = (Name)o;
		return name.equals(name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
