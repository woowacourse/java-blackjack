package blackjack.domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @Test
    @DisplayName("금액이 정상 생성된다")
    void moneyInitializeTest() {
        assertDoesNotThrow(() -> new Money(5000));
    }

    @ParameterizedTest(name = "입력금액 : {0}")
    @DisplayName("1000원 이상 100,000원 이하의 범위에서 벗어나면 예외를 발생시킨다")
    @ValueSource(ints = {900, 101_000})
    void moneyRangeExceptionTest(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Money.OUT_OF_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("1000원단위 외의 입력에서 예외를 발생한다")
    void unitAmountExceptionTest() {
        assertThatThrownBy(() -> new Money(5100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Money.UNIT_AMOUNT_EXCEPTION_TEST);
    }

}
