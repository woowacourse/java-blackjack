package domain;

import domain.participant.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyTest {

    @Test
    @DisplayName("양의 정수를 파라미터로 주입하면 money 객체가 정상적으로 생성된다")
    void generateMoney() {
        int money = 1000;

        assertDoesNotThrow(() -> new Money(money));
    }

    @ParameterizedTest
    @DisplayName("0 또는 음수를 파라미터로 주입하여 money 객체를 생성하면 예외가 발생한다")
    @ValueSource(ints = {-100, -1})
    void ErrorTestGeneratingMoney(int money) {

        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음수는 입력할 수 없습니다.");
    }
}
