package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("돈")
public class MoneyTest {
    @Test
    @DisplayName("으로 숫자가 아닌 형식이 입력될 경우 예외가 발생한다.")
    void validateNonNumeric() {
        // given
        String money = "만원";

        // when & then
        assertThatCode(() -> Money.from(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 아닙니다.");
    }

    @Test
    @DisplayName("으로 음수가 입력되면 예외가 발생한다.")
    void validatePositive() {
        // given
        String money = "-10000";

        // when & then
        assertThatCode(() -> Money.from(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅할 금액을 음수일 수 없습니다.");
    }
}
