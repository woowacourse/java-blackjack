package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

	@DisplayName("이름의 길이가 1 이상 5 이하라면 정상적으로 생성된다.")
	@ParameterizedTest
	@ValueSource(strings = {"a", "abcde"})
	void playerNameLengthSuccessTest(String name) {
		assertThatCode(() -> new Player(name))
			.doesNotThrowAnyException();
	}

	@DisplayName("이름의 길이가 1 미만 또는 5 초과이면 예외를 발생시킨다.")
	@ParameterizedTest
	@ValueSource(strings = {"", "abcdef"})
	void playerNameLengthErrorTest(String name) {
		assertThatThrownBy(() -> new Player(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Name.LENGTH_ERROR_MESSAGE);
	}
}
