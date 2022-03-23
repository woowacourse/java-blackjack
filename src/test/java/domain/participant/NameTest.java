package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import utils.ExceptionMessages;

class NameTest {

    @ParameterizedTest
    @EmptySource
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest(String input) {
        assertThatThrownBy(() -> Name.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("입력받은 이름 중에, 공백이 있는 경우 예외를 발생한다.")
    void emptyNameTest() {
        List<String> names = List.of("", "   ", "kun");
        assertThatThrownBy(() -> Name.from(names))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }
}