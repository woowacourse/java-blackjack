package blackjack.model;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingAmountTest {

    @Test
    @DisplayName("플레이어가 패배하면 배팅 금액을 잃는다.")
    void loseAmountTest() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1000);
        // when & then
        assertThat(bettingAmount.calculateProfit(GameResult.LOSE)).isEqualTo(new Profit(-1000));
    }

    @Test
    @DisplayName("플레이어가 승리 시 배팅 금액만큼의 추가 수익을 얻는다.")
    void winAmountTest() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1000);
        // when & then
        assertThat(bettingAmount.calculateProfit(GameResult.WIN)).isEqualTo(new Profit(1000));
    }

    @Test
    @DisplayName("무승부 시 추가 수익은 발생하지 않는다.")
    void drawAmountTest() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1000);
        // when & then
        assertThat(bettingAmount.calculateProfit(GameResult.DRAW)).isEqualTo(new Profit(0));
    }

    @Test
    @DisplayName("플레이어가 블랙잭 승리 시 배팅 금액의 1.5배를 수익으로 얻는다.")
    void blackjackAmountTest() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1000);
        // when & then
        assertThat(bettingAmount.calculateProfit(GameResult.BLACKJACK)).isEqualTo(new Profit(1500));
    }

    @Test
    @DisplayName("배팅 금액이 0원 초과이면 통과")
    void reateSuccessWhenAmountIsPositive() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1);
        // when & then
        assertThat(bettingAmount.getMoney()).isEqualTo(1);
    }

    @Test
    @DisplayName("배팅 금액이 0원 이하면 예외처리한다.")
    void createFailWhenAmountIsZeroOrLess() {
        // when & then
        assertThatThrownBy(() -> new BettingAmount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.AMOUNT_NOT_ZERO.getMessage());

        assertThatThrownBy(() -> new BettingAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.AMOUNT_NOT_ZERO.getMessage());
    }
}
