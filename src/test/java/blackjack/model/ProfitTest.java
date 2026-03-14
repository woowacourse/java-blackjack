package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @Test
    @DisplayName("수익의 부호를 반전시킨 새로운 Profit 객체를 반환한다.")
    void negateTest() {
        // given
        Profit profit = new Profit(1000);
        Profit negativeProfit = new Profit(-1500);
        // when & then
        assertThat(profit.negate()).isEqualTo(new Profit(-1000));
        assertThat(negativeProfit.negate()).isEqualTo(new Profit(1500));
    }

    @Test
    @DisplayName("다른 Profit 객체와 값을 더한 새로운 Profit 객체를 반환한다.")
    void addTest() {
        // given
        Profit profit1 = new Profit(1000);
        Profit profit2 = new Profit(500);
        // when & then
        assertThat(profit1.add(profit2)).isEqualTo(new Profit(1500));
    }

    @Test
    @DisplayName("내부의 수익 값(double)이 같으면 완전히 동등한 객체로 취급한다.")
    void equalsAndHashCodeTest() {
        // given
        Profit profit1 = new Profit(1000.0);
        Profit profit2 = new Profit(1000.0);
        Profit profit3 = new Profit(500.0);
        // when & then
        assertThat(profit1).isEqualTo(profit2);
        assertThat(profit1.hashCode()).isEqualTo(profit2.hashCode());
        assertThat(profit1).isNotEqualTo(profit3);
    }
}
