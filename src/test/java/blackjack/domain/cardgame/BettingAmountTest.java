package blackjack.domain.cardgame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingAmountTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 베팅_금액이_양의_정수가_아닌_경우_예외가_발생한다(int input) {
        // when & then
        Assertions.assertThatCode(() -> new BettingAmount(input))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
