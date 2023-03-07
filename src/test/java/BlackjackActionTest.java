import domain.BlackjackAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackActionTest {
    @DisplayName("입력값에 따라 명령이 생성된다. y -> HIT")
    @Test
    void createCommandHitSuccessTest() {
        String input = "y";

        BlackjackAction blackjackAction = BlackjackAction.from(input);
        Assertions.assertThat(blackjackAction).isEqualTo(BlackjackAction.HIT);
    }

    @DisplayName("입력값에 따라 명령이 생성된다. n -> HOLD")
    @Test
    void createCommandHoldSuccessTest() {
        String input = "n";

        BlackjackAction blackjackAction = BlackjackAction.from(input);
        Assertions.assertThat(blackjackAction).isEqualTo(BlackjackAction.HOLD);
    }

    @ParameterizedTest(name = "y 혹은 n 만 입력할 수 있다.")
    @ValueSource(strings = {"yy", "nn", "hold", "hit"})
    void createCommandFailTest(String input) {
        Assertions.assertThatThrownBy(() -> BlackjackAction.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
