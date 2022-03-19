package blackjack.domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MoneyTest {

    @Test
    @DisplayName("배팅금액은 1000원 이상이어야 한다.")
    void thrownWhenMoneyUnderMinimum() {
        assertThatThrownBy(() -> new Money(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1000이상이어야 합니다.");
    }
}
