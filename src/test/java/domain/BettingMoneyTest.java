package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"천원", "10000원", "5000?", "50$", "3만원"})
    void 숫자가_아닌_입력값으로_생성할_때_예외발생_테스트(String input) {
        Assertions.assertThatThrownBy(() -> new BettingMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자로만 구성된 입력값이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void 최소입력값보다_작은값을_입력했을_때_예외발생_테스트(String input) {
        Assertions.assertThatThrownBy(() -> new BettingMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이상이어야 합니다.");
    }
}
