package model.betting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @DisplayName("배팅금이 숫자가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"1a", "만원", "$10"})
    void testInvalidBetInput(String amount) {
        assertThatThrownBy(() -> new Bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅금이 0 이하면 예외 발생")
    @Test
    void testInvalidBetRange() {
        assertThatThrownBy(() -> new Bet("0"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅금이 10원 단위가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"1", "1002", "999999", "10203"})
    void testInvalidBetUnit(String amount) {
        assertThatThrownBy(() -> new Bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅금이 0보다 큰 정수이고 10원 단위이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"10", "100", "2000"})
    void testValidBet(String amount) {
        assertThatCode(() -> new Bet(amount)).doesNotThrowAnyException();
    }
}
