package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameResultTest {
    @ParameterizedTest
    @DisplayName("최종 게임 결과의 반대로 계산하는 기능")
    @MethodSource("provideFinalGameResult")
    void reverseResult(List<GameResult> origin, List<GameResult> expected) {

        assertThat(GameResult.reverse(origin)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideFinalGameResult() {
        return Stream.of(
                Arguments.of(List.of(WIN), List.of(LOSE)),
                Arguments.of(List.of(DRAW), List.of(DRAW)),
                Arguments.of(List.of(LOSE), List.of(WIN)),
                Arguments.of(List.of(WIN, WIN, DRAW), List.of(LOSE, LOSE, DRAW)),
                Arguments.of(List.of(WIN, DRAW, LOSE), List.of(LOSE, DRAW, WIN))
        );
    }
}
