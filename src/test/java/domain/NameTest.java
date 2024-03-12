package domain;

import domain.participant.Name;
import exception.InvalidPlayerNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void BlankInputThrowException(String value) {
        Assertions.assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(InvalidPlayerNameException.class);
    }

    @DisplayName("null을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void nullInputThrowException(String value) {
        Assertions.assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(InvalidPlayerNameException.class);
    }
}
