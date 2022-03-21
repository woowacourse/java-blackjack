package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BetMoneyTest {

    @ParameterizedTest(name = "[{index}] {1}원")
    @CsvSource({"10000, 10_000", "20000, 20_000", "50000, 50_000"})
    @DisplayName("입력값을 받아 베팅 금액을 생성한다.")
    void createBetMoney(String input, long value) {
        BetMoney betMoney = new BetMoney(input);

        assertThat(betMoney.getMoney()).isEqualTo(new Money(value));
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"엘리", "lisa"})
    @DisplayName("입력값이 숫자가 아닐 경우 예외를 발생시킨다.")
    void throwExceptionWhenNotNumber(String input) {
        assertThatThrownBy(() -> new BetMoney(input))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"-10000", "0"})
    @DisplayName("입력값이 0 또는 음수일 경우 예외를 발생시킨다.")
    void throwExceptionWhenNotPositiveNumber(String input) {
        assertThatThrownBy(() -> new BetMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
