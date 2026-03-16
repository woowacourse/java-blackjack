package model.judgement;

import static org.assertj.core.api.Assertions.assertThat;

import model.paticipant.BetAmount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ResultStatusTest {

    @ParameterizedTest(name = "베팅 금액이 1000원 일 때 게임 결과가 {0}이라면, {1}원의 수익이 발생한다")
    @MethodSource("fixture.ResultStatusTestFixture#천_원_베팅에_따른_게임_결과별_수익_정보_제공")
    void 게임_결과에_따라_베팅_금액의_수익을_계산한다(ResultStatus resultStatus, int expectedProfit) {
        // given
        BetAmount betAmount = new BetAmount(1000);

        // when
        Profit profit = resultStatus.calculateProfit(betAmount);

        // then
        assertThat(profit.value()).isEqualTo(expectedProfit);
    }

}