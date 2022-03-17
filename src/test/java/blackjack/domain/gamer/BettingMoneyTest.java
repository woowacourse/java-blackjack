package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {
    @Test
    @DisplayName("입력 값이 100단위가 아니라면 예외가 발생한다.")
    void validateMoney() {
        assertThatThrownBy(() -> {
            new BettingMoney(10);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 100단위만 가능합니다.");
    }

}