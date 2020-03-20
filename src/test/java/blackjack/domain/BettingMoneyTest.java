package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @DisplayName("생성자 테스트")
    @Test
    void Money() {
        assertThatThrownBy(() -> BettingMoney.of(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");

        assertThatThrownBy(() -> BettingMoney.of("-1000"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("양수");

        assertThatThrownBy(() -> BettingMoney.of("0"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("양수");

        assertThatThrownBy(() -> BettingMoney.of("1234"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("최소 단위");

        assertThatThrownBy(() -> BettingMoney.of("가"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("숫자");
    }

    @Test
    @DisplayName("금액 곱하기")
    void multiply() {
        BettingMoney bettingMoney = BettingMoney.of("1000");
        assertThat(bettingMoney.multiply(1)).isEqualTo(1000);
        assertThat(bettingMoney.multiply(0)).isEqualTo(0);
        assertThat(bettingMoney.multiply(-1)).isEqualTo(-1000);
        assertThat(bettingMoney.multiply(1.5)).isEqualTo(1500);
    }
}
