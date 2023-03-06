import domain.PlayerAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerActionTest {

    @DisplayName("입력값에 따라 명령이 생성된다. y -> HIT")
    @Test
    void createCommandHitSuccessTest() {
        String input = "y";

        PlayerAction command = PlayerAction.from(input);
        assertThat(command).isEqualTo(PlayerAction.HIT);
    }

    @DisplayName("입력값에 따라 명령이 생성된다. n -> HOLD")
    @Test
    void createCommandHoldSuccessTest() {
        String input = "n";

        PlayerAction command = PlayerAction.from(input);
        assertThat(command).isEqualTo(PlayerAction.HOLD);
    }

    @ParameterizedTest(name = "y 혹은 n 만 입력할 수 있다.")
    @ValueSource(strings = {"yy", "nn", "hold", "hit"})
    void createCommandFailTest(String input) {
        Assertions.assertThatThrownBy(() -> PlayerAction.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
