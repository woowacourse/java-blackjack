package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Test
    @DisplayName("배팅 금액이 양수 인지 확인한다.")
    void validateMoneyRange() {
        // expect
        assertThatThrownBy(() -> new BettingMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 0원 이상이여야 합니다.");
    }

    @Test
    @DisplayName("배팅 금액의 1.5배를 반환한다.")
    void getBlackjackMoney() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        long prize = bettingMoney.getBlackjackPrize();

        // then
        assertThat(prize).isEqualTo(15000);
    }

    @Test
    @DisplayName("배팅 금액을 반환한다.")
    void getPrize() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        long prize = bettingMoney.getPrize();

        // then
        assertThat(prize).isEqualTo(10000);
    }
}
