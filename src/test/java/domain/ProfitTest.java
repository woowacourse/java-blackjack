package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("저장된 금액을 음수로 변환하여 반환한다.")
    @Test
    void loseProfitWhenLoseGame() {
        Profit profit = new Profit(10);
        assertThat(profit.lose().getValue()).isEqualTo(-10);
    }

    @DisplayName("저장된 금액을 0으로 바꾸어 반환한다.")
    @Test
    void noProfitWhenDraw() {
        Profit profit = new Profit(10);
        assertThat(profit.keep().getValue()).isEqualTo(0);
    }

    @DisplayName("저장된 금액을 반환한다.")
    @Test
    void winProfitWhenWinGame() {
        Profit profit = new Profit(10);
        assertThat(profit.win().getValue()).isEqualTo(10);
    }

    @DisplayName("저장된 금액의 1.5배를 반환한다.")
    @Test
    void winSpecialProfitWhenBlackjack() {
        Profit profit = new Profit(10);
        assertThat(profit.specialWin().getValue()).isEqualTo(15);
    }
}