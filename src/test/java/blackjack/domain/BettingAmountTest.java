package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.result.ResultStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @Test
    @DisplayName("문자열을 통해서 배팅 금액을 생성한다.")
    void BattingAmount_Instance_create_with_Integer() {
        assertThatCode(() -> {
            new BettingAmount("1000");
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-10000", "abac", "", " "})
    @DisplayName("배팅 금액에 양수가 아닌 값이 들어오는 경우 예외처리된다.")
    void BattingAmount_Instance_only_create_with_positive_number(String value) {
        assertThatThrownBy(() -> {
            new BettingAmount(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("승리(블랙잭X)시 배팅 금액의 1배로 수익을 생성한다.")
    public void BattingAmount_Create_profit_once_battingAmount_when_win() {
        BettingAmount battingAmount = new BettingAmount("10000");
        assertThat(battingAmount.calculateProfit(ResultStatus.WIN)).isEqualTo(new Profit(10000));
    }

    @Test
    @DisplayName("패배시 배팅 금액의 -1배로 수익을 생성한다.")
    public void BattingAmount_Create_profit_minus_once_battingAmount_when_lose() {
        BettingAmount battingAmount = new BettingAmount("10000");
        assertThat(battingAmount.calculateProfit(ResultStatus.LOSE)).isEqualTo(new Profit(-10000));
    }

    @Test
    @DisplayName("무승부시 배팅 금액의 0배로 수익을 생성한다.")
    public void BattingAmount_Create_profit_zero_battingAmount_when_draw() {
        BettingAmount battingAmount = new BettingAmount("10000");
        assertThat(battingAmount.calculateProfit(ResultStatus.DRAW)).isEqualTo(new Profit(0));
    }

    @Test
    @DisplayName("블랙잭으로 승리시 배팅 금액의 1.5배로 수익을 생성한다.")
    public void BattingAmount_Create_profit_one_and_half_profit_battingAmount_when_blackjack_win() {
        BettingAmount battingAmount = new BettingAmount("10000");
        System.out.println(battingAmount.calculateProfit(ResultStatus.BLACKJACK).getValue());
        assertThat(battingAmount.calculateProfit(ResultStatus.BLACKJACK)).isEqualTo(new Profit(15000));
    }
}
