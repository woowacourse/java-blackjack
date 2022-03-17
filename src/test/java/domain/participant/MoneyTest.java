package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ExceptionMessages;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -100})
    @DisplayName("Money에 0이나 음수가 들어온 경우 예외를 발생한다.")
    void zeroMoneyErrorTest(int money) {
        assertThatThrownBy(() -> new Money(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.NOT_POSITIVE_MONEY_ERROR);
    }
}
