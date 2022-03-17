package blackjack.domain.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("입력한 금액이 0 또는 음수일 때 예외 처리 확인")
    void zeroOrNegativeTest() {
        Assertions.assertThatThrownBy(() -> new Money(-10000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("0 또는 음수는 입력할 수 없습니다. 정수를 입력해 주세요.");
    }
}
