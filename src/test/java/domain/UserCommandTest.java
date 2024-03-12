package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserCommandTest {
    @DisplayName("y나 n가 아닌 입력은 예외발생")
    @Test
    void invalidInput() {
        final String input = "no";

        Assertions.assertThrows(IllegalArgumentException.class, () -> UserCommand.fromInput(input));
    }

    @DisplayName("y나 n가 아닌 입력은 예외발생")
    @Test
    void invalidInput2() {
        final String input = "yes";

        Assertions.assertThrows(IllegalArgumentException.class, () -> UserCommand.fromInput(input));
    }
}