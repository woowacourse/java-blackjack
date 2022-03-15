package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("RedrawChoice 테스트")
class RedrawChoiceTest {

	@Nested
	@DisplayName("주어진 선택이")
	class RedrawChoiceInputTest {

		@DisplayName("올바른 선택이면 그에 해당하는 객체를 반환한다")
		@ParameterizedTest(name = "{index} {displayName} choice={0} expectedChoice={1}")
		@CsvSource(value = {"y, YES", "Y, YES"})
		void check_Proper_Choice(final String choice, final RedrawChoice expectedChoice) {
			final RedrawChoice actualChoice = RedrawChoice.of(choice);
			assertThat(actualChoice).isEqualTo(expectedChoice);
		}

		@Test
		@DisplayName("올바르지 않으면 예외가 발생한다")
		void check_Invalid_Choice() {
			assertThatThrownBy(
				() -> RedrawChoice.of("e")
			).isInstanceOf(IllegalArgumentException.class);
		}
	}

}