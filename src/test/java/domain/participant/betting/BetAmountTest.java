package domain.participant.betting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BetAmountTest {
    @Test
    void 최소_베팅_금액_보다_작으면_예외가_발생한다() {
        int invalidBetAmount = 0;

        assertThatThrownBy(() -> new BetAmount(invalidBetAmount))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 최소_베팅_금액_보다_크거나_같으면_정상적으로_객체를_생성한다() {
        int validBetAmount = 1;

        assertThatCode(() -> new BetAmount(validBetAmount))
                .doesNotThrowAnyException();
    }
}
