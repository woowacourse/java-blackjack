package domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackActionTest {
    @DisplayName("CommandValue가 y면 HIT을 반환한다.")
    @Test
    void createCommandHitSuccessTest() {
        String commandValue = "y";

        BlackjackAction blackjackAction = BlackjackAction.from(commandValue);
        Assertions.assertThat(blackjackAction).isEqualTo(BlackjackAction.HIT);
    }

    @DisplayName("CommandValue가 n이면 HOLD을 반환한다.")
    @Test
    void createCommandHoldSuccessTest() {
        String commandValue = "n";

        BlackjackAction blackjackAction = BlackjackAction.from(commandValue);
        Assertions.assertThat(blackjackAction).isEqualTo(BlackjackAction.HOLD);
    }

    @ParameterizedTest(name = "CommandValue는 y 혹은 n 만 가능하다")
    @ValueSource(strings = {"yy", "nn", "hold", "hit"})
    void createCommandFailTest(String input) {
        Assertions.assertThatThrownBy(() -> BlackjackAction.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
