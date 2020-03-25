package blackjack.domain.yesOrNo;

import blackjack.domain.yesOrNo.exceptions.YesOrNoException;
import blackjack.domain.yesOrNo.YesOrNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class YesOrNoTest {
	private static final String UPPERCASE_Y = "Y";
	private static final String LOWERCASE_Y = "y";
	private static final String UPPERCASE_N = "N";
	private static final String LOWERCASE_N = "n";
	private static final String EMPTY = "";
	private static final String NULL = null;
	private static final String Z = "z";
	private static final String DOUBLE_Y = "yy";

	@DisplayName("of()가 적절한 값이 들어왔을시 인스턴스를 반환하는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_Y, LOWERCASE_Y, UPPERCASE_N, LOWERCASE_N})
	void of_YOrN_IsNotNull(String yOrN) {
		assertThat(YesOrNo.of(yOrN)).isNotNull();
	}

	@DisplayName("of()가 적절하지 않은 값이 들어왔을시 예외를 던지는지 테스트")
	@ParameterizedTest
	@MethodSource("of_NotYOrN_ThrowYesOrNoException")
	void of_NotYOrN_ThrowYesOrNoException(String invalidInput) {
		assertThatThrownBy(() -> YesOrNo.of(invalidInput))
				.isInstanceOf(YesOrNoException.class);
	}

	static Stream<String> of_NotYOrN_ThrowYesOrNoException() {
		return Stream.of(EMPTY, Z, DOUBLE_Y);
	}

	@DisplayName("of()가 null 값이 들어왔을 시 예외를 던지는지 테스트")
	@Test
	void of_Null_ThrowYesOrNoException() {
		assertThatThrownBy(() -> YesOrNo.of(NULL))
				.isInstanceOf(YesOrNoException.class);
	}

	@DisplayName("isYes()가 y/Y일 시 true를 반환하는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_Y, LOWERCASE_Y})
	void isYes_Y_ReturnTrue(String yes) {
		assertThat(YesOrNo.of(yes).isYes()).isTrue();
	}

	@DisplayName("isYes()가 n/N일 시 false를 반환하는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_N, LOWERCASE_N})
	void isYes_N_ReturnFalse(String no) {
		assertThat(YesOrNo.of(no).isYes()).isFalse();
	}
}