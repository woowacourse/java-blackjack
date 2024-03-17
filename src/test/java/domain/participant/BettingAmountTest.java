package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BettingAmountTest {
    @Test
    void 최소_베팅_금액_보다_작으면_예외가_발생한다() {
        int invalidBettingAmount = 0;

        assertThatThrownBy(() -> new BettingAmount(invalidBettingAmount))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 최소_베팅_금액_보다_크거나_같으면_정상적으로_객체를_생성한다() {
        int validBettingAmount = 1;

        assertThatCode(() -> new BettingAmount(validBettingAmount))
                .doesNotThrowAnyException();
    }
}
