package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 1000})
    @DisplayName("100원 단위로 설정한 금액에 대한 Money 를 생성할 수 있다.")
    void createMoney(int amount) {
        // when
        Money money = new Money(amount);

        // then
        assertAll(
            () -> assertThat(money).isNotNull(),
            () -> assertThat(money.getAmount()).isEqualTo(amount)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 99, 98})
    @DisplayName("Money 를 생성하려면 금액이 100원 보다 커야한다.")
    void createMoneyOverAmount10(int amount) {
        // then
        assertThatThrownBy(() -> new Money(amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 금액은 100원 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 110, 2350, 10030})
    @DisplayName("Money 를 생성하려면 금액이 100원 단위여야한다.")
    void createMoneyUnit10(int amount) {
        // then
        assertThatThrownBy(() -> new Money(amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 금액은 100원 단위어야 합니다.");
    }
}
