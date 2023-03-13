package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.exception.InvalidBetMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @Test
    @DisplayName("배팅 금액은 10000원 이상 100000원 이하여야 한다")
    void no_error_bet() {
        assertDoesNotThrow(() -> Money.bet(50000));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10000, 0, 200000})
    @DisplayName("배팅 금액이 10000원 미만이거나 100000원 초과이면 예외가 발생한다")
    void error_bet(int value) {
        assertThrows(InvalidBetMoneyException.class, () -> Money.bet(value));
    }

    @Test
    @DisplayName("더하기")
    void plus() {
        Money money = Money.bet(10000);
        Money plusAmount = Money.bet(20000);
        assertThat(money.plus(plusAmount)).isEqualTo(Money.bet(30000));
    }

    @Test
    @DisplayName("부호 바꾸기")
    void reverse() {
        Money money = Money.bet(10000);
        assertThat(money.reverse().getValue()).isEqualTo(-10000);
    }
}
