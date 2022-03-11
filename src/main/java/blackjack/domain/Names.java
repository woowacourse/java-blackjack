package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Names {
	private static final String EMPTY_NAMES = "[ERROR] 플레이어를 한명 이상 입력해야 합니다.";
	private final List<Name> names;

	public Names(List<String> names) {
		validateEmptyNames(names);
		this.names = new ArrayList<>();
		for (String name : names) {
			this.names.add(new Name(name));
		}
	}

	private void validateEmptyNames(List<String> names) {
		if (names.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAMES);
		}
	}

	public List<Name> getNames() {
		return names;
	}
}
