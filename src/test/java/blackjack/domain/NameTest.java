package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {
    @DisplayName("이름에 정상적인 길이의 입력값이 들어왔을 때, Name 객체를 생성할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"abcde", "a1234", "12345", "a"})
    void validNameLengthTest(String validLengthName) {
        assertDoesNotThrow(() -> new Name(validLengthName));
    }

    @DisplayName("이름의 길이가 1미만 15초과일 때 Name객체는 생성되지 않는다")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"1234567890123456"})
    void invalidNameLengthTest(String invalidLengthName) {
        assertThatThrownBy(() -> new Name(invalidLengthName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름에 영어/숫자/한글 이외의 문자가 있다면 Name객체를 생성할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"-", "a@"})
    void invalidNameRuleTest(String invalidLengthName) {
        assertThatThrownBy(() -> new Name(invalidLengthName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
