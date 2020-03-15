package blackjack.domain.result;

import blackjack.domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.result.ResultType.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {

    @DisplayName("플레이어의 승/패/무 결과 확인")
    @ParameterizedTest
    @CsvSource(value = {"15, 12, WIN", "15, 20, LOSE", "15, 15, DRAW", "15, 22, WIN", "22, 15, LOSE", "22, 22, LOSE", "21, 21, DRAW"})
    void test1(int playerScore, int dealerScore, ResultType expectedResult) {
        ResultType actualResult = ResultType.findResultByScore(playerScore, dealerScore);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("승/패/무 reverse 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, LOSE", "DRAW, DRAW", "LOSE, WIN"})
    void test2(ResultType resultType, ResultType expectedType) {
        ResultType actualType = resultType.reverse();
        assertThat(actualType).isEqualTo(expectedType);
    }

    @DisplayName("전달받은 플레이어 결과 리스트에서 해당 ResultType 개수 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, 2", "DRAW, 2", "LOSE, 1"})
    void test3(ResultType resultType, int expectedCount) {
        List<PlayerResult> playersResult = Arrays.asList(
                new PlayerResult(new Name("포비"), WIN),
                new PlayerResult(new Name("쪼밀리"), DRAW),
                new PlayerResult(new Name("타미"), LOSE),
                new PlayerResult(new Name("워니"), WIN),
                new PlayerResult(new Name("CU"), DRAW)
        );

        int actualCount = resultType.countSameResultType(playersResult);

        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
