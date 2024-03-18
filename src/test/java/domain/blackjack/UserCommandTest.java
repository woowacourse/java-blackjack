package domain.blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCommandTest {
    @DisplayName("y나 n가 아닌 입력은 예외발생")
    @Test
    void invalidInput() {
        final String input = "no";

        assertThrows(IllegalArgumentException.class, () -> UserCommand.fromInput(input));
    }

    @DisplayName("y나 n가 아닌 입력은 예외발생")
    @Test
    void invalidInput2() {
        final String input = "yes";

        assertThrows(IllegalArgumentException.class, () -> UserCommand.fromInput(input));
    }
}
