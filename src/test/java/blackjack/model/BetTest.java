package blackjack.model;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @Test
    @DisplayName("올바른 금액이면 Bet 객체 생성")
    void test_success_when_valid_amount() {
        assertDoesNotThrow(() -> new Bet(1000));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("0원 이하로는 베팅 불가")
    void test_fail_when_invalid_amount(int betAmount) { //Non-positive
        assertThatThrownBy(() -> new Bet(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
