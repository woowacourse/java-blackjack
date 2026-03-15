package domain.betting;

import domain.betiing.BetAmount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BettingAmountTest {

    @Test
    void 입력받은_배팅금액을_저장한다() {
        // when
        BetAmount betAmount = BetAmount.from(1000);

        // then
        Assertions.assertThat(betAmount.amount()).isEqualTo(1000);
    }

    @Test
    void 배팅_금액이_음수인_경우_예외가_발생한다() {
        // when & then
        Assertions.assertThatThrownBy(() -> BetAmount.from(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
