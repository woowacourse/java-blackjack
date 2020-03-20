package domain.participant;

public class Name {
	private String name;

	private Name(String name) {
		validate(name);
		this.name = name;
	}

	public static Name create(String name) {
		return new Name(name);
	}

	private void validate(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름은 blank값이 될 수 없습니다.");
		}
	}

	public String getName() {
		return this.name;
	}
}
