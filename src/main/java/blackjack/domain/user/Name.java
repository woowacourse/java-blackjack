package blackjack.domain.user;

public class Name {
	public static final String EMPTY_NAME_EXCEPTION = "이름이 빈 입력이면 안됩니다.";

	private final String name;

	public Name(final String name) {
		final String blankRemovedName = removeBlank(name);
		validateEmptyName(blankRemovedName);
		this.name = blankRemovedName;
	}

	private String removeBlank(final String name) {
		return name.trim();
	}

	private void validateEmptyName(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION);
		}
	}

	public String getName() {
		return name;
	}
}
