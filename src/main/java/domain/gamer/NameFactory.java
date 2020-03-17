package domain.gamer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import util.StringUtil;

public class NameFactory {
	public static List<Name> create(String input) {
		List<String> userNames = StringUtil.parseByComma(input);
		List<Name> names = userNames.stream()
			.map(Name::new)
			.collect(Collectors.toList());
		return Collections.unmodifiableList(names);
	}
}