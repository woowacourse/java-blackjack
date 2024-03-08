package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.InvalidHitCommandException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerActionTest {
    private static Stream<Arguments> provideCommandWithAction() {
        return Stream.of(
                Arguments.of("y", PlayerAction.HIT),
                Arguments.of("n", PlayerAction.STAND)
        );
    }

    @DisplayName("유효하지 않은 명령어가 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N", " ", "yn", "mason"})
    void invalidHitCommandTest(String command) {
        assertThatThrownBy(() -> PlayerAction.getAction(command))
                .isInstanceOf(InvalidHitCommandException.class);
    }

    @DisplayName("입력된 명령어에 해당하는 PlayerAction을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideCommandWithAction")
    void getActionTest(String command, PlayerAction playerAction) {
        assertThat(PlayerAction.getAction(command))
                .isEqualTo(playerAction);
    }
}
