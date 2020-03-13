package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class YesOrNoTest {
	@DisplayName("플레이어의 추가 드로우 응답 값이 y, n이 아닌 경우, 잘못된 인자 예외 발생")
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"a", "응", "아니", "3"})
	void validateRightYesOrNoTest(String input) {
		assertThatThrownBy(() -> YesOrNo.of(input)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("응답 값 y,n에 따라 플레이어의 추가 드로우 여부 판단")
	@ParameterizedTest
	@CsvSource(value = {"YES,true", "NO,false"})
	void isMoreDrawTest(YesOrNo response, boolean expected) {
		assertThat(response.isYes()).isEqualTo(expected);
	}
}