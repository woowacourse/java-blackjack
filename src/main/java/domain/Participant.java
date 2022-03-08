package domain;

public class Participant {

	public static final String BLANK_NAME_ERROR_MESSAGE = "[Error] 이름은 공백이거나 빈칸일 수 없습니다.";
	protected final String name;

	public Participant(String name) {
		validateName(name);
		this.name = name;
	}

	protected void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(BLANK_NAME_ERROR_MESSAGE);
		}
	}
}
