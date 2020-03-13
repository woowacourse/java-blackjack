package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 승")
    @Test
    void computeResult_playerWin() {
        ResultType resultType = ResultType.computeResult(20, 12);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 패")
    @Test
    void computeResult_playerLose() {
        ResultType resultType = ResultType.computeResult(10, 12);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 무승부")
    @Test
    void computeResult_playerDraw() {
        ResultType resultType = ResultType.computeResult(20, 20);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 패 - 플레이어 21초과, 딜러 21초과X")
    @Test
    void computeResult_playerLose_playerExceed_21() {
        ResultType resultType = ResultType.computeResult(22, 20);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 패 - 플레이어 21초과, 딜러 21초과")
    @Test
    void computeResult_playerLose_playerAndDealerExceed_21() {
        ResultType resultType = ResultType.computeResult(22, 22);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 계산 : 플레이어 승 - 플레이어 21초과X, 딜러 21초과")
    @Test
    void computeResult_playerWin_dealerExceed_21() {
        ResultType resultType = ResultType.computeResult(20, 22);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승/패/무승부 횟수 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN,2", "DRAW,1", "LOSE,1"})
    void computeSumTest(ResultType resultType, int expected) {
        List<ResultType> resultTypes = Arrays.asList(ResultType.WIN, ResultType.WIN, ResultType.DRAW, ResultType.LOSE);

        int actual = ResultType.computeSum(resultType, resultTypes);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("승/패/무승부의 적절한 반대 값을 찾는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN,LOSE", "DRAW,DRAW", "LOSE,WIN"})
    void reverseTest(ResultType input, ResultType expected) {
        ResultType actual = ResultType.reverse(input);
        assertThat(actual).isEqualTo(expected);
    }
}
