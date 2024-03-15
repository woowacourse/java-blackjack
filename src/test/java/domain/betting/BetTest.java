package domain.betting;

import static domain.betting.Bet.INVALID_BETTING_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    @DisplayName("베팅액은 1원 이상이다.")
    void createSuccess() {
        assertThatCode(() -> new Bet(2))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1원 미만으로 생성하면, 예외가 발생한다.")
    void createFail() {
        assertThatCode(() -> new Bet(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BETTING_MESSAGE);
    }

}
