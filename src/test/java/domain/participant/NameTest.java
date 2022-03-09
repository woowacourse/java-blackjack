package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class NameTest {

    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest() {
        String name = "";

        assertThatThrownBy(() -> new Name(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

}