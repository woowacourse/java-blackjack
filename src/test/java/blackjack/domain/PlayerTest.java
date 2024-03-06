package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("플레이어 이름은 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }
}
