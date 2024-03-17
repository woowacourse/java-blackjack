package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidPlayerNameException;
import exception.ReservedPlayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void BlankInputThrowException(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(InvalidPlayerNameException.class);
    }

    @DisplayName("null을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void nullInputThrowException(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(InvalidPlayerNameException.class);
    }

    @ParameterizedTest
    @DisplayName("참여자 이름이 딜러이면 예외가 발생한다.")
    @ValueSource(strings = {"딜러", "딜러 ", " 딜러", " 딜러 "})
    void validateName(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(ReservedPlayerNameException.class);
    }
}
