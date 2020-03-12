package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 우승 경우")
    @Test
    void name() {
        ResultType resultType = ResultType.findResultByScore(20, 12);

        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우")
    @Test
    void name2() {
        ResultType resultType = ResultType.findResultByScore(10, 12);

        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 무승부 경우")
    @Test
    void name3() {
        ResultType resultType = ResultType.findResultByScore(20, 20);

        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우 - 플레이어가 21을 초과하고, 딜러는 21을 초과하지 않을 때")
    @Test
    void name4() {
        ResultType resultType = ResultType.findResultByScore(22, 20);

        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우 - 플레이어와 딜러 모두 21을 초과할 때")
    @Test
    void name5() {
        ResultType resultType = ResultType.findResultByScore(20, 12);

        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 승 경우 - 딜러가 21을 초과하고, 플레이어는 21초과하지 않을 때")
    @Test
    void name6() {
        ResultType resultType = ResultType.findResultByScore(20, 12);

        assertThat(resultType).isEqualTo(ResultType.WIN);
    }
}
