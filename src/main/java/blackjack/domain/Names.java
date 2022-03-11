package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Names {
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
			throw new IllegalArgumentException("플레이어를 한명 이상 입력해야 합니다.");
		}
	}

	public List<Name> getNames() {
		return names;
	}
}
