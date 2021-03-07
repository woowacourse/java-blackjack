package blakcjack.domain.name;

import java.util.*;

import static blakcjack.domain.name.DuplicatePlayerNamesException.DUPLICATE_NAME_ERROR;

public class Names {
	private final List<Name> names = new ArrayList<>();

	public Names(List<String> names) {
		validateDuplication(names);
		addNames(names);
	}

	private void validateDuplication(final List<String> names) {
		final Set<String> nameGroup = new HashSet<>(names);
		if (nameGroup.size() != names.size()) {
			throw new DuplicatePlayerNamesException(DUPLICATE_NAME_ERROR);
		}
	}

	private void addNames(final List<String> names) {
		for (String name : names) {
			this.names.add(new Name(name));
		}
	}

	public List<Name> toList() {
		return Collections.unmodifiableList(names);
	}
}
