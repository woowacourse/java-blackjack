package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
	@Test
	@DisplayName("입력받은 문자열이 null인지 검증")
	void validateNull() {
		assertThatThrownBy(() -> {
			new Name(null);
		}).isInstanceOf(NullPointerException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "  "})
	@DisplayName("입력받은 문자열이 공백인지 검증")
	void validateSpace(String name) {
		assertThatThrownBy(() -> {
			new Name(name);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"allen,kyle,bee", "pobi,jason,cu,woni,brown,jun"})
	@DisplayName("Name의 list가 제대로 생성되는지 검사")
	void createTest(String input) {
		List<Name> names = Name.list(input);
		List<Name> expectedNames = StringUtil.parseByComma(input)
				.stream()
				.map(Name::new)
				.collect(Collectors.toList());

		assertThat(names).isEqualTo(expectedNames);
	}

	@Test
	void namesUnmodifiableTest() {
		List<Name> names = Name.list("pobi,jason,cu");

		assertThatThrownBy(() -> {
			names.add(new Name("brown"));
		}).isInstanceOf(UnsupportedOperationException.class);
	}
}