package domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {
	@ParameterizedTest
	@ValueSource(strings = {"y", "Y", "n", "N"})
	@DisplayName("올바른 YesOrNo를 입력한 경우 테스트")
	void createTest(String input) {
		Answer answer = Answer.from(input);

		assertThat(answer).isInstanceOf(Answer.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"aa", "", " Y "})
	@DisplayName("올바르지 않은 YesOrNo를 입력한 경우 테스트")
	void createInvalidTest(String input) {
		assertThatThrownBy(() -> {
			Answer.from(input);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageMatching("올바르지 않은 대답입니다.");
	}

	@Test
	@DisplayName("Yes의 isYes 테스트")
	void yesIsYesTest() {
		assertThat(Answer.YES.isYes()).isTrue();
	}

	@Test
	@DisplayName("No의 isYes 테스트")
	void noIsNotYesTest() {
		assertThat(Answer.NO.isYes()).isFalse();
	}
}