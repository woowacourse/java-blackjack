package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;

public class Name {
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
			throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION.getMessage());
		}
	}

	public String getName() {
		return name;
	}
}
