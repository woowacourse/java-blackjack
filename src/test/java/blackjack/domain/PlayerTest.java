package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @ParameterizedTest(name = "[{index}] 플레이어명 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어명에 공백이 존재하면 예외 발생")
    void blankPlayerNameExceptionTest(final String name) {

        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 공백이 될 수 없습니다.");
    }
}