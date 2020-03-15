package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class PlayerIntentionTypeTest {

	@DisplayName("y입력시 YES, n입력시 NO 반환 확인")
	@ParameterizedTest
	@CsvSource(value = {"y,YES", "n,NO"})
	void create_When_Right_Input_Return_PlayerIntentionType(String input, PlayerIntentionType expected) {
		assertThat(PlayerIntentionType.of(input)).isEqualTo(expected);
	}

	@DisplayName("잘못된 입력시 예외처리")
	@Test
	void create_When_Wrong_Input_Throw_NullPointerException() {
		assertThatNullPointerException().isThrownBy(() -> PlayerIntentionType.of("k"))
				.withMessage("옳지 않은 입력입니다.");
	}
}