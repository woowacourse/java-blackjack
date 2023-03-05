package blackjack.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CommandTest {

    @ParameterizedTest(name = "input : {0}")
    @CsvSource(value = {"y:YES", "n:STAY"}, delimiter = ':')
    @DisplayName("of()는 인자로 y를 받으면 YES 를 반환하고 n 을 받으면 STAY 를 반환한다.")
    void of_test(String inputCommand, String command) {
        // then
        Assertions.assertThat(Command.of(inputCommand)).isEqualTo(Command.valueOf(command));
    }

    @Test
    @DisplayName("of()는 인자로 n,y가 아닌 다른 값을 받으면 예외처리 한다.")
    void of_validate_test_() {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Command.of("k"))
                .withMessage("y 또는 n 으로 입력해주세요.");
    }

    @Test
    @DisplayName("isYes()는 이넘 YES 이면 true 를 반환한다.")
    void is_yes_true_test() {
        // given & when
        Command command = Command.YES;

        // then
        Assertions.assertThat(command.isYes()).isTrue();
    }

    @Test
    @DisplayName("isYes()는 이넘 STAY 이면 false 를 반환한다.")
    void is_yes_false_test() {
        // given & when
        Command command = Command.STAY;

        // then
        Assertions.assertThat(command.isYes()).isFalse();
    }
}
