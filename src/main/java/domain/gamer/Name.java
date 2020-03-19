package domain.gamer;

import java.util.Objects;

/**
 *   이름을 나타내는 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu
 */
public class Name {
	private String name;

	public Name(String name) {
		validateNullAndEmpty(name);
		this.name = name;
	}

	private void validateNullAndEmpty(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Name name1 = (Name)o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public String getName() {
		return name;
	}
}