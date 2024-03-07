package blackjack.domain.gamer;

import blackjack.view.PlayerCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class PlayerCommandTest {
    @Test
    @DisplayName("존재하지 않는 명령어가 입력 될 경우 예외가 발생한다.")
    void validateExist() {
        // given
        String command = "fr";
        String errorMessage = "존재하지 않는 명령어 입니다.";

        // when & then
        assertThatCode(() -> PlayerCommand.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(errorMessage);
    }
}
