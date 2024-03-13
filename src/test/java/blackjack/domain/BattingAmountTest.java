package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingAmountTest {

    @Test
    @DisplayName("숫자를 통해서 배팅 금액을 생성한다.")
    void BattingAmount_Instance_create_with_Integer() {
        assertThatCode(() -> {
            new BattingAmount(1000);
        }).doesNotThrowAnyException();
    }
}
