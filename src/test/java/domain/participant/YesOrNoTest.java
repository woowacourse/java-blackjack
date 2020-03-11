package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class YesOrNoTest {
	@ParameterizedTest
	@ValueSource(strings = {"y", "Y", "n", "N"})
	@DisplayName("올바른 YesOrNo를 입력한 경우 테스트")
	void createTest(String input) {
		YesOrNo yesOrNo = YesOrNo.of(input);

		assertThat(yesOrNo).isInstanceOf(YesOrNo.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"aa", "", " Y "})
	@DisplayName("올바르지 않은 YesOrNo를 입력한 경우 테스트")
	void createInvalidTest(String input) {
		assertThatThrownBy(() -> {
			YesOrNo.of(input);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("Y 또는 N을 입력해야합니다.");
	}

	@Test
	@DisplayName("isYes 테스트")
	void isYesTest() {
		assertThat(YesOrNo.YES.isYes()).isTrue();
		assertThat(YesOrNo.NO.isYes()).isFalse();
	}
}