package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("수익률 테스트")
class ProfitTest {
    @DisplayName("블랙잭으로 이기면 수익은 배팅금액의 1.5배이다.")
    @Test
    void blackJackProfitTest() {
        // given, when
        Profit profit = Profit.of(new BetAmount(1000), MatchResult.BLACKJACK);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(1500);
    }

    @DisplayName("숫자 비교로 이기면 수익은 배팅 금액이다.")
    @Test
    void normalWinProfitTest() {
        // given, when
        Profit profit = Profit.of(new BetAmount(1000), MatchResult.WIN);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(1000);
    }

    @DisplayName("지면 배팅 금액만큼 잃는다")
    @Test
    void loseProfitTest() {
        // given, when
        Profit profit = Profit.of(new BetAmount(1000), MatchResult.LOSE);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(-1000);
    }

    @DisplayName("비기면 낸 수익이 0원이다")
    @Test
    void drawProfitTest() {
        // given, when
        Profit profit = Profit.of(new BetAmount(1000), MatchResult.DRAW);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(0);
    }
}