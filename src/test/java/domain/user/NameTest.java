package domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("플레이어의 이름은 ")
class NameTest {
    @DisplayName("공백만 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void blankTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Name(input));
    }

    @DisplayName("\"딜러\"로 설정하면 예외가 발생한다.")
    @Test
    void invalidNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Name("딜러"));
    }
}
