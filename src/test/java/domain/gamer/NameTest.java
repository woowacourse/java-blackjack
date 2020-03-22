package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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

	@Test
	void namesUnmodifiableTest() {
		List<Name> names = PlayersFactory.newNames("pobi,jason,cu");

		assertThatThrownBy(() -> {
			names.add(new Name("brown"));
		}).isInstanceOf(UnsupportedOperationException.class);
	}
}