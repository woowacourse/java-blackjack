package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static domain.game.Result.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    @Test
    @DisplayName("정반대의 결과를 반환한다.")
    void reverseTest() {
        List<Result> results = List.of(WIN, DRAW, LOSE);
        List<Result> reverseResult = results.stream().map(Result::reverse).toList();

        assertThat(reverseResult).isEqualTo(List.of(LOSE, DRAW, WIN));
    }

    @ParameterizedTest()
    @CsvSource(value = {"1,LOSE", "2,DRAW", "3,WIN"})
    @DisplayName("두 결과를 비교하여 승패를 판단한다.")
    void compareTest(int current, Result result) {
        int opponentValue = 2;

        assertThat(Result.compare(current, opponentValue)).isEqualTo(result);
    }
}
