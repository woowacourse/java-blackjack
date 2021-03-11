package blackjack.domain.participant;

import static blackjack.controller.BlackJackController.*;

import java.util.Objects;

public class PlayerName {
	private static final String ERROR_MESSAGE_WITH_SPACE = "이름에 공백이 포함됩니다.";
	private static final String SPACE = " ";
	private static final String EMPTY = "";

	private final String name;

	public PlayerName(String name) {
		this.name = name;
		validate(name);
	}

	private void validate(String name) {
		validateEmpty(name);
		validateSpace(name);
	}

	private void validateEmpty(String name) {
		if (name.equals(EMPTY)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_INPUT);
		}
	}

	private void validateSpace(String name) {
		if (name.contains(SPACE)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_WITH_SPACE);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlayerName that = (PlayerName)o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
