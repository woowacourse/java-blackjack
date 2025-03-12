package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {

    @DisplayName("배팅 금액은 1,000원 이상 100,000원 이하여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 10000, 100000})
    void 배팅_금액은_1000원_이상_100000원_이하여야_한다(final int amount) {

        // given

        // when & then
        assertThatCode(() -> new BetAmount(amount))
                .doesNotThrowAnyException();

    }

    @DisplayName("배팅 금액은 1,000원 이상 100,000원 이하가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 100001, -10000})
    void 배팅_금액은_1000원_이상_100000원_이하가_아니라면_예외가_발생한다(final int amount) {

        // given

        // when & then
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
