package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AnswerTest {
	@ParameterizedTest
	@ValueSource(strings = {"y", "Y", "n", "N"})
	@DisplayName("올바른 YesOrNo를 입력한 경우 테스트")
	void createTest(String input) {
		Answer answer = Answer.of(input);

		assertThat(answer).isInstanceOf(Answer.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"aa", "", " Y "})
	@DisplayName("올바르지 않은 YesOrNo를 입력한 경우 테스트")
	void createInvalidTest(String input) {
		assertThatThrownBy(() -> {
			Answer.of(input);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("isYes 테스트")
	void isYesTest() {
		assertThat(Answer.YES.isYes()).isTrue();
		assertThat(Answer.NO.isYes()).isFalse();
	}
}