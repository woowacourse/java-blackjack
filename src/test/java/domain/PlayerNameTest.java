package domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {
    @ParameterizedTest
    @DisplayName("플레이어 이름으로 공백이 들어오면 예외를 반환합니다.")
    @ValueSource(strings = {"", " ", "   "})
    void validateBlankNameTest(String value) {
        Assertions.assertThatThrownBy(() -> new PlayerName(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어있을 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("한글과 영문이 아닌 이름은 예외를 반환합니다.")
    @ValueSource(strings = {";", ",", ">>", "../"})
    void validatePlayerNameTest(String value) {
        Assertions.assertThatThrownBy(() -> new PlayerName(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글과 영문만 가능합니다.");
    }
}
