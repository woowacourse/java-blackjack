package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

	@ParameterizedTest
	@ValueSource(strings = {"a", "abcde"})
	@DisplayName("이름의 길이가 1글자에서 5글자인 경우 예외가 발생되지 않는다.")
	void validNameLengthTest(String name) {
		assertThatCode(() -> new Name(name)).doesNotThrowAnyException();
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "abcdef"})
	@DisplayName("이름의 길이가 0글자이거나, 5글자를 초과하면 예외가 발생된다.")
	void invalidNameLengthTest(String name) {
		assertThatThrownBy(() -> new Name(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름의 길이는 최소 1글자부터 최대 5글자까지 가능합니다.");
	}
}
