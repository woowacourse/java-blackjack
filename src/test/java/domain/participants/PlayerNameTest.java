package domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {
    @ParameterizedTest
    @DisplayName("플레이어 이름으로 공백이 들어오면 예외를 반환합니다.")
    @ValueSource(strings = {"", " ", "   "})
    void validatePlayerName(String value) {
        assertThatThrownBy(() -> new PlayerName(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어있을 수 없습니다.");
    }
}