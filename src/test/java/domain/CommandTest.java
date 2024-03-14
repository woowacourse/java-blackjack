package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    @ParameterizedTest
    @CsvSource(value = {"y,YES", "n,NO"})
    @DisplayName("입력에 따라 커맨드를 반환한다.")
    void getTest(String input, Command command) {
        assertThat(Command.get(input)).isEqualTo(command);
    }

    @Test
    @DisplayName("지정되지 않은 입력 시 예외를 던진다.")
    void invalidInputThrowsException() {
        assertThatThrownBy(() -> Command.get("aaa"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
