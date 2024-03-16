package model.betting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @DisplayName("배팅금이 0보다 큰 정수가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -20})
    void testInvalidBetRange(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅금이 10원 단위가 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 1002, 999999, 10203})
    void testInvalidBetUnit(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅금이 0보다 큰 정수이고 10원 단위이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {10, 110, 20000, 5555500})
    void testValidBet(int amount) {
        assertThatCode(() -> new Bet(amount)).doesNotThrowAnyException();
    }
}
