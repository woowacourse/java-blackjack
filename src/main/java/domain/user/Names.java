package domain.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Names {
	private static final String SPLIT_DELIMITER = ",";

	private List<Name> names;

	public Names(List<Name> names) {
		this.names = names;
	}

	private static Names of(List<Name> names) {
		return new Names(names);
	}

	public static Names of(String names) {
		String trimmedNames = names.trim();
		return of(Arrays.stream(trimmedNames.split(SPLIT_DELIMITER))
				.map(String::trim)
				.map(Name::new)
				.collect(Collectors.toList()));
	}

	public List<Name> getNames() {
		return Collections.unmodifiableList(names);
	}
}
