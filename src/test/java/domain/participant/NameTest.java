package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import utils.ExceptionMessages;

class NameTest {

    @ParameterizedTest
    @EmptySource
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest(String input) {
        assertThatThrownBy(() -> new Name(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }
}