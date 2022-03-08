package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> new Player(input, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> new Player(input, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 플레이어의 이름은 공백이 들어올 수 없습니다.");
    }

    @Nested
    @DisplayName("유저의 턴 종료여부를 확인할 수 있다.")
    class IsEndTurn {

        @Test
        @DisplayName("종료하는 경우 true를 반환한다.")
        void endTurn() {
            final Player player = new Player("user", true);
            assertTrue(player.isEndPlayer());
        }

        @Test
        @DisplayName("종료하지 않는 경우 false를 반환한다.")
        void notEndTurn() {
            final Player player = new Player("user", false);
            assertFalse(player.isEndPlayer());
        }
    }
}
