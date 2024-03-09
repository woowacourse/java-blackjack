package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerActionTest {
    @DisplayName("명령어에 해당되는 PlayerAction을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideCommandWithAction")
    void getActionTest(boolean dosePlayerWantHit, PlayerAction expectedPlayerAction) {
        assertThat(PlayerAction.getAction(dosePlayerWantHit))
                .isEqualTo(expectedPlayerAction);
    }

    private static Stream<Arguments> provideCommandWithAction() {
        return Stream.of(
                Arguments.of(true, PlayerAction.HIT),
                Arguments.of(false, PlayerAction.STAND)
        );
    }
}
