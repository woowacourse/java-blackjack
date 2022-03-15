package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    @DisplayName("베팅 금액은 0원 이하면 예외를 반환한다.")
    void lessThanOrEqualsToZero() {
        assertThatThrownBy(() -> new Betting(0)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0원 이하일 수 없습니다.");
    }
}
