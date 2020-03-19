package blackjack.domain.result;

import blackjack.domain.user.component.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {
    @DisplayName("플레이어 BlackJack 확인: 플레이어가 BlackJack이고, 딜러가 BlackJack이 아닌 경우")
    @Test
    void test1() {
        ResultType resultType = ResultType.computeResult(new Point(21, true), new Point(12, false));
        assertThat(resultType).isEqualTo(ResultType.BLACK_JACK);
    }

    @DisplayName("플레이어 승 - 플레이어/딜러 모두 21미만")
    @Test
    void computeResult_playerWin() {
        ResultType resultType = ResultType.computeResult(new Point(20, false), new Point(12, false));
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 승 - 플레이어 21초과X, 딜러 21초과")
    @Test
    void computeResult_playerWin_dealerExceed_21() {
        ResultType resultType = ResultType.computeResult(new Point(20, false), new Point(22, false));
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 승 - 플레이어 BlackJack이 아닌 21, 딜러 21미만")
    @Test
    void computeResult_playerWin_notBlackJack() {
        ResultType resultType = ResultType.computeResult(new Point(21, false), new Point(20, false));
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 패 - 플레이어/딜러 모두 21미만")
    @Test
    void computeResult_playerLose() {
        ResultType resultType = ResultType.computeResult(new Point(10, false), new Point(12, false));
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 무승부 - 같은 점수")
    @Test
    void computeResult_playerDraw() {
        ResultType resultType = ResultType.computeResult(new Point(20, false), new Point(20, false));
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어 무승부 - 둘 다 블랙잭")
    @Test
    void computeResult_playerBlackJackDraw() {
        ResultType resultType = ResultType.computeResult(new Point(21, true), new Point(21, true));
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어 패 - 플레이어 21초과, 딜러 21초과X")
    @Test
    void computeResult_playerLose_playerExceed_21() {
        ResultType resultType = ResultType.computeResult(new Point(22, false), new Point(20, false));
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 패 - 플레이어 21초과, 딜러 21초과")
    @Test
    void computeResult_playerLose_playerAndDealerExceed_21() {
        ResultType resultType = ResultType.computeResult(new Point(22, false), new Point(22, false));
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 패 - 플레이어 BlackJack이 아닌 21, 딜러 BlackJack")
    @Test
    void computeResult_playerLose_dealerBlackJack() {
        ResultType resultType = ResultType.computeResult(new Point(21, false), new Point(21, true));
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }


    @DisplayName("승/패/무승부의 적절한 반대 값을 찾는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN,LOSE", "DRAW,DRAW", "LOSE,WIN"})
    void reverseTest(ResultType input, ResultType expected) {
        ResultType actual = ResultType.opposite(input);
        assertThat(actual).isEqualTo(expected);
    }
}
