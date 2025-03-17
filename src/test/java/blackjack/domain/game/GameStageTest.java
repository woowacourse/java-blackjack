package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameStageTest {

    @ParameterizedTest
    @DisplayName("현재 단계의 다음 단계를 구할 수 있다.")
    @MethodSource
    void canNext(GameStage currentStage, GameStage expectedNextStage) {
        GameStage actualNextStage = currentStage.next();

        assertThat(actualNextStage).isEqualTo(expectedNextStage);
    }

    static Stream<Arguments> canNext() {
        return Stream.of(
                Arguments.of(GameStage.PREPARATION, GameStage.PLAYING),
                Arguments.of(GameStage.PLAYING, GameStage.RESULT_OUTPUT),
                Arguments.of(GameStage.RESULT_OUTPUT, GameStage.RESULT_OUTPUT)
        );
    }
}