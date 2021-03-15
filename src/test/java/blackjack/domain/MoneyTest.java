package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Money 객체 생성된다.")
    void createMoneyTest() {
        Money money = Money.of("10000");

        assertThat(money).isInstanceOf(Money.class);
    }

    @ParameterizedTest(name = "Money 입력값이 null이면 예외가 발생합니다.")
    @NullSource
    void moneyInputNull(String input) {
        assertThatThrownBy(() -> {
            Money.of(input);
        }).isInstanceOf(NullPointerException.class).hasMessage("베팅 입력값은 null이 될 수 없습니다.");
    }

    @ParameterizedTest(name = "Money 입력값이 empty이면 예외가 발생합니다.")
    @EmptySource
    void moneyInputEmpty(String input) {
        assertThatThrownBy(() -> {
            Money.of(input);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("베팅 입력값은 빈값이 될 수 없습니다.");
    }

    @Test
    @DisplayName("Money 입력값으로 문자열을 입력하면 예외가 발생합니다.")
    void moneyInputString() {
        assertThatThrownBy(() -> {
            Money.of("이건 문자열이다.");
        }).isInstanceOf(NumberFormatException.class).hasMessage("베팅 입력값은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("Money 입력값으로 음수를 입력하면 예외가 발생합니다.")
    void moneyInputNegativeNumber() {
        assertThatThrownBy(() -> {
            Money.of("-10000");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("베팅 입력값은 1이상의 양의 정수 여야합니다.");
    }
}