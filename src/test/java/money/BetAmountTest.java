package money;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {
    @ParameterizedTest(name = "배팅 금액은 1보다 큰 정수이어야 한다.")
    @ValueSource(strings = {"천원", "-1999", "1000.24"})
    void createMoneyFailTest(String input) {
        assertThatThrownBy(() -> BetAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
