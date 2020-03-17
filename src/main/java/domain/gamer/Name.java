package domain.gamer;

import util.StringUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Name {
	private final String name;

	public Name(String name) {
		validate(name);
		this.name = name;
	}

	public static List<Name> list(String input) {
		List<String> userNames = StringUtil.parseByComma(input);

		return userNames.stream()
				.map(Name::new)
				.collect(Collectors.toUnmodifiableList());
	}

	private void validate(String name) {
		validateNull(name);
		validateSpace(name);
	}

	private void validateNull(String name) {
		if (Objects.isNull(name)) {
			throw new NullPointerException("이름은 null이 될 수 없습니다.");
		}
	}

	private void validateSpace(String name) {
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
		}
	}

	public String getName() {
		return name;
	}
}