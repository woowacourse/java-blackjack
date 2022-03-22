package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @Test
    @DisplayName("존재하지 않는 명령어를 입력하였을 경우")
    void not_exist_command() {

        assertThatThrownBy(() -> Command.of("x"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"n", "N", "y", "Y"})
    @DisplayName("존재하는 명령어를 입력하였을 경우")
    void exist_command(String input) {

        Assertions.assertDoesNotThrow(() -> Command.of(input));
    }

}
