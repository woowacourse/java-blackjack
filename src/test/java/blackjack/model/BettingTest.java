package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    @DisplayName("베팅 금액 테스트")
    void bettingMoneyTest() {
        Betting betting = new Betting(10000);
        assertThat(betting.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("유효하지 않은 베팅 금액 테스트")
    void validateMoneyTest() {
        assertThatThrownBy(() -> new Betting(1250))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 금액입니다.");
    }
}
