package blackjack.domain.user;

import blackjack.domain.user.exceptions.YesOrNoException;
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

	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_Y, LOWERCASE_Y, UPPERCASE_N, LOWERCASE_N})
	void of_YOrN_IsNotNull(String yOrN) {
		assertThat(YesOrNo.of(yOrN)).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("of_NotYOrN_ThrowYesOrNoException")
	void of_NotYOrN_ThrowYesOrNoException(String invalidInput) {
		assertThatThrownBy(() -> YesOrNo.of(invalidInput))
				.isInstanceOf(YesOrNoException.class);
	}

	static Stream<String> of_NotYOrN_ThrowYesOrNoException() {
		return Stream.of(EMPTY, Z, DOUBLE_Y);
	}

	@Test
	void of_Null_ThrowYesOrNoException() {
		assertThatThrownBy(() -> YesOrNo.of(NULL))
				.isInstanceOf(YesOrNoException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_Y, LOWERCASE_Y})
	void isYes_Y_ReturnTrue(String yes) {
		assertThat(YesOrNo.of(yes).isYes()).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {UPPERCASE_N, LOWERCASE_N})
	void isYes_N_ReturnFalse(String no) {
		assertThat(YesOrNo.of(no).isYes()).isFalse();
	}
}