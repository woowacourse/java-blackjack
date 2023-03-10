package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidMoneyAmountException;
import blackjack.domain.player.exception.InvalidMoneyUnitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    @DisplayName("배팅 전 돈은 0원이다")
    void initial_money() {
        assertThat(Money.zero().getAmount()).isZero();
    }

    @Nested
    @DisplayName("배팅 시 돈은 1,000원 이상 1,000,000원 이하여야 한다")
    class ValidateMoney {

        @Test
        @DisplayName("1000원 미만이면 예외가 발생한다")
        void under_1000() {
            assertThrows(InvalidMoneyAmountException.class,
                    () -> Money.from(999)
            );
        }

        @Test
        @DisplayName("1,000,000원 초과면 예외가 발생한다")
        void over_1000000() {
            assertThrows(InvalidMoneyAmountException.class,
                    () -> Money.from(1_000_001)
            );
        }

        @ParameterizedTest
        @ValueSource(ints = {1000, 10000, 100000, 1000000})
        @DisplayName("천원~백만원 사이의 금액이면 예외가 발생하지 않는다")
        void no_exception(int value) {
            assertThatCode(() -> Money.from(value))
                    .doesNotThrowAnyException();
        }
    }

    @Test
    @DisplayName("10원 단위가 아닌 경우 예외가 발생한다")
    void invalid_money_unit_exception() {
        assertThrows(InvalidMoneyUnitException.class,
                () -> Money.from(2023)
        );
    }
}