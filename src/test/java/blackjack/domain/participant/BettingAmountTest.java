package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.ExceptionMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    private static final int BETTING_UNIT = 1_000;

    @ParameterizedTest
    @ValueSource(ints = {1999, 200_001})
    void 베팅_가능한_금액_범위를_벗어나면_예외가_발생한다(int bettingAmount) {
        // when & then
        assertThatThrownBy(() -> new BettingAmount(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.BETTING_AMOUNT_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {5500, 199100})
    void 베팅_가능한_금액_단위가_아니면_예외가_발생한다(int bettingAmount) {
        // when & then
        assertThatThrownBy(() -> new BettingAmount(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_BETTING_UNIT.getMessage(BETTING_UNIT));
    }
}
