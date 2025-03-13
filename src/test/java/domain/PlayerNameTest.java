package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {
    @ParameterizedTest
    @DisplayName("플레이어 이름으로 공백이 들어오면 예외를 반환합니다.")
    @ValueSource(strings = {"", " ", "   "})
    void validateBlankNameTest(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerName(value));
    }

    @ParameterizedTest
    @DisplayName("한글과 영문이 아닌 이름은 예외를 반환합니다.")
    @ValueSource(strings = {";", ",", ">>", "../"})
    void validatePlayerNameTest(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerName(value));
    }
}
