package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @DisplayName("배팅 금액이 올바르게 입력 되었는지 확인한다.")
    @Test
    void money_create() {
        Money money = Money.of("5000");

        assertThat(money.getMoney()).isEqualTo(5000);
    }

    @DisplayName("null 혹은 빈값일 떄 예외 발생을 확인한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void money_null_or_empty_exception(String input) {
        assertThatThrownBy(() -> Money.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("양수 이외의 값을 입력 받았을 때 예외 발생을 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "-1", "0"})
    void money_non_positive_exception(String input) {
        assertThatThrownBy(() -> Money.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 양수로 입력해주세요.");
    }

    @DisplayName("10의 단위 이외의 값을 입력 받았을 때 예외 발생을 확인한다.")
    @Test
    void money_ten_units_number_exception() {
        assertThatThrownBy(() -> Money.of("5001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 10원 단위로 입력해주세요.");
    }

    @DisplayName("수익률을 따라 올바른 수익금이 반환되는지 확인한다.")
    @Test
    void money_reverse_create() {
        Money money = Money.of("1000");
        Money reverseMoney = money.profits(-1.5);

        assertThat(reverseMoney.getMoney()).isEqualTo(-1500);
    }
}
