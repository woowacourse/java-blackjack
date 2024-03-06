package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

	@ParameterizedTest
	@ValueSource(strings = {"", "abcdef"})
	@DisplayName("이름의 길이가 0글자이거나, 5글자를 초과하면 예외가 발생된다.")
	void nameLengthExceptionTest(String name) {
		assertThatThrownBy(() -> new Name(name))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
