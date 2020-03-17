package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import util.StringUtil;

class NameFactoryTest {
	@ParameterizedTest
	@ValueSource(strings = {"allen,kyle,bee", "pobi,jason,cu,woni,brown,jun"})
	@DisplayName("Player의 list가 제대로 생성되는지 검사")
	void createTest(String input) {
		List<Name> names = NameFactory.create(input);
		List<String> expectedNames = StringUtil.parseByComma(input);

		for (Name name : names) {
			assertThat(expectedNames).contains(name.getName());
		}
	}

	@Test
	void namesUnmodifiableTest() {
		List<Name> names = NameFactory.create("pobi,jason,cu");

		assertThatThrownBy(() -> {
			names.add(new Name("brown"));
		}).isInstanceOf(UnsupportedOperationException.class);
	}
}