package blackjack.domain.participant;

import blackjack.exception.InvalidHitCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerActionTest {
    @DisplayName("올바르지 않은 명령어가 들어오면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"yn", "Y", "", "N", "감자튀김은 맛있어"})
    void invalidHitCommandTest(String command) {
        assertThatThrownBy(() -> PlayerAction.getAction(command))
                .isInstanceOf(InvalidHitCommandException.class);
    }

    @DisplayName("명령어에 해당되는 PlayerAction을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideCommandWithAction")
    void getActionTest(String command, PlayerAction expectedPlayerAction) {
        assertThat(PlayerAction.getAction(command))
                .isEqualTo(expectedPlayerAction);
    }

    private static Stream<Arguments> provideCommandWithAction() {
        return Stream.of(
                Arguments.of("y", PlayerAction.HIT),
                Arguments.of("n", PlayerAction.STAND)
        );
    }
}
