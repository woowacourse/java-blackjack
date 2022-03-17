package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetMoneyTest {

    @Test
    @DisplayName("베팅 금액은 1000원 이상이어야 한다.")
    void validate_min() {
        Assertions.assertThatThrownBy(() -> new BetMoney(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("베팅 금액은 1000원 단위만 가능하다.")
    void validate_unit() {
        Assertions.assertThatThrownBy(() -> new BetMoney(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 1000원 단위로만 가능합니다.");
    }

    @Test
    @DisplayName("레버리지를 받아 수익을 계산한다.")
    void calculate_revenue() {
        BetMoney betMoney = new BetMoney(10000);

        double actual = betMoney.calculateRevenue(1.5);

        assertThat(actual).isEqualTo(15000);
    }
}
