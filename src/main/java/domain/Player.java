package domain;

public class Player {
	private String name;

	public Player(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름이 공백입니다.");
		}
	}
}
