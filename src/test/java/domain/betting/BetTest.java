package domain.betting;

import static domain.betting.Bet.INVALID_BETTING_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetTest {

    @Test
    @DisplayName("베팅액은 천원 이상 백만원 이하이다.")
    void createSuccess() {
        assertThatCode(() -> new Bet(10000))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("천원 미만, 백만원 초과된 값으로 초기화하면 예외가 발생한다.")
    @ValueSource(ints = {999, 1_000_001})
    void createFail(int money) {
        assertThatCode(() -> new Bet(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BETTING_MESSAGE);
    }

}
