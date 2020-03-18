package domain;

public class Name {
	private static final String BLANK = " ";

	private final String name;

	public Name(String name) {
		validate(name);
		this.name = name;
	}

	private void validate(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름이 비어있다.");
		}
		if (name.contains(BLANK)) {
			throw new IllegalArgumentException("이름에 공백이 존재한다.");
		}
	}

	public String getValue() {
		return name;
	}
}
