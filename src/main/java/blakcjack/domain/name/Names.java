package blakcjack.domain.name;

import java.util.*;

public class Names {
	private final List<Name> names = new ArrayList<>();

	public Names(List<String> names) {
		validateDuplication(names);
		addNames(names);
	}

	private void validateDuplication(final List<String> names) {
		final Set<String> nameGroup = new HashSet<>(names);
		if (nameGroup.size() != names.size()) {
			throw new DuplicatePlayerNamesException();
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

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Names names1 = (Names) o;
		return Objects.equals(names, names1.names);
	}

	@Override
	public int hashCode() {
		return Objects.hash(names);
	}
}
