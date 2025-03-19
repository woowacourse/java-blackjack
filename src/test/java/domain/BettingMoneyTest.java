package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.CustomException;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @DisplayName("게임 결과에 따른 수익을 반환한다")
    @Test
    void test() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);
        GameResultStatus gameResultStatus = GameResultStatus.WIN;

        // when
        Profit profit = bettingMoney.calculateProfit(gameResultStatus);

        // then
        assertThat(profit).isEqualTo(new Profit(BigDecimal.valueOf(10000)));
    }

    @DisplayName("베팅 금액이 음수라면 예외를 발생시킨다")
    @Test
    void test2() {
        // given
        int bettingMoney = -10000;

        // when & then
        assertThatThrownBy(() -> {
            new BettingMoney(bettingMoney);
        }).isInstanceOf(CustomException.class)
                .hasMessageContaining("베팅 금액은 음수일 수 없습니다.");
    }

    @DisplayName("베팅 금액이 1000원 단위가 아니라면 예외를 발생시킨다")
    @Test
    void test3() {
        // given
        int bettingMoney = 10300;

        // when & then
        assertThatThrownBy(() -> {
            new BettingMoney(bettingMoney);
        }).isInstanceOf(CustomException.class)
                .hasMessageContaining("베팅 금액은 1000원 단위여야 합니다.");
    }
}
