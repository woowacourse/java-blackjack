package domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {

    @ParameterizedTest
    @DisplayName("배팅 금액이 양수가 아니면 예외가 발생한다.")
    @CsvSource(value = {"-1", "0"})
    void should_throw_exception_when_money_is_not_positive(int value) {
        // when & then
        assertThatThrownBy(() -> new Money(value));
    }
}
