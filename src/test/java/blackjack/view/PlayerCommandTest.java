package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("플레이어 명령어")
class PlayerCommandTest {
    @Test
    @DisplayName("가 존재하지 않을 경우 예외가 발생한다.")
    void validateExist() {
        // given
        String command = "fr";

        // when & then
        assertThatCode(() -> PlayerCommand.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 명령어 입니다.");
    }
}
