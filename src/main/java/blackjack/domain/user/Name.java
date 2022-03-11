package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;

import java.util.regex.Pattern;

public class Name {
	private static final Pattern BLANK = Pattern.compile("[\\s]+");

	private final String name;

	public Name(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION.getMessage());
		}
		if (BLANK.matcher(name).matches()) {
			throw new IllegalArgumentException(BLANK_NAME_EXCEPTION.getMessage());
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
