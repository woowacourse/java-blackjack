package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @DisplayName("배팅 금액 정상 생성 테스트")
    @Test
    public void testCreateBettingMoney() {
        // given & when & then
        assertThatCode(() -> new BettingMoney(10000))
                .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액 생성 단위 테스트")
    @Test
    public void testBettingMoneyUnit() {
        // given & when & then
        assertThatThrownBy(() -> new BettingMoney(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액 음수 테스트")
    @Test
    public void testBettingMoneyNegative() {
        // given & when & then
        assertThatThrownBy(() -> new BettingMoney(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수익금 계산 테스트")
    @Test
    public void testRevenue() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);

        //when
        int revenue = bettingMoney.calculateRevenue(1.5);

        //then
        assertThat(revenue).isEqualTo(1500);
    }
}