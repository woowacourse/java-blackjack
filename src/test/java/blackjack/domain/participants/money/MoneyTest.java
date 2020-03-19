package blackjack.domain.participants.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.exceptions.InvalidMoneyException;

class MoneyTest {
    @DisplayName("잘못된 돈 생성자")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"abc", "0", "-100"})
    void invalidConstructor(String input) {
        assertThatThrownBy(() -> {
            Money.create(input);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    @DisplayName("돈 덧셈")
    @Test
    void add() {
        // given
        String amount = "1000";
        Money money = Money.create(amount);
        Money other = Money.create(amount);
        // when
        Money actual = money.add(other);
        // then
        assertThat(actual.getAmount()).isEqualTo(2000);
    }

    @DisplayName("돈 뺄셈")
    @Test
    void subtract() {
        // given
        String amount = "1000";
        Money money = Money.create(amount);
        Money other = Money.create(amount);
        // when
        Money actual = money.subtract(other);
        // then
        assertThat(actual.getAmount()).isEqualTo(0);
    }

    @DisplayName("돈 곱셈")
    @Test
    void multiply() {
        // given
        String amount = "1000";
        Money money = Money.create(amount);
        Money other = Money.create(amount);
        // when
        Money actual = money.multiply(1.5);
        // then
        assertThat(actual.getAmount()).isEqualTo(1500);
    }
}
