import domain.Command;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("입력값에 따라 명령이 생성된다. y -> HIT")
    @Test
    void createCommandHitSuccessTest() {
        String input = "y";

        Command command = Command.from(input);
        Assertions.assertThat(command).isEqualTo(Command.HIT);
    }

    @DisplayName("입력값에 따라 명령이 생성된다. n -> HOLD")
    @Test
    void createCommandHoldSuccessTest() {
        String input = "n";

        Command command = Command.from(input);
        Assertions.assertThat(command).isEqualTo(Command.HOLD);
    }

    @ParameterizedTest(name = "y 혹은 n 만 입력할 수 있다.")
    @ValueSource(strings = {"yy","nn","hold","hit"})
    void createCommandFailTest(String input) {
        Assertions.assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
