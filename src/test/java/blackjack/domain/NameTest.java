package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @EmptySource
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    void throwExceptionWhenNameIsBlank(final String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "1a3", "베로", "헤나"})
    @DisplayName("이름이 영문이 아닐 경우 예외가 발생한다.")
    void throwExceptionWhenNameIsNotAlphabet(final String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}