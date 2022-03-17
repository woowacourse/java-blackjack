package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {


    @Test
    @DisplayName("베팅 금액이 1000으로 나눠지지 않을 때 테스트")
    void isSmallerThan1000() {
        assertThatThrownBy(() -> new Money(1100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅 금액이 음수일 때 테스트")
    void nonDivisibleBy1000() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
