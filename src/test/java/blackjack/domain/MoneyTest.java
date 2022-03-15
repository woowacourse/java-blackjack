package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest(name = "[{index}] {0}원")
    @ValueSource(ints = {-10_000, 0, 10_000})
    @DisplayName("원하는 돈을 생성한다.")
    void generateMoney(int value) {
        final Money money = Money.from(value);

        assertThat(money.getValue()).isEqualTo(value);
    }

    @ParameterizedTest(name = "[{index}] {1}원")
    @CsvSource({"10000, 10_000", "20000, 20_000", "50000, 50_000"})
    @DisplayName("입력값을 받아 돈을 생성한다.")
    void generateMoneyByString(String input, int value) {
        final Money money = Money.from(input);

        assertThat(money.getValue()).isEqualTo(value);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"-10000", "0", "lisa"})
    @DisplayName("입력값이 양수가 아닐 경우 예외를 발생시킨다.")
    void throwExceptionWhenNotPositiveNumber(String input) {
        assertThatThrownBy(() -> Money.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 양수여야 합니다.");
    }
}
