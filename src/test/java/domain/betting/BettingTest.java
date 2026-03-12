package domain.betting;

import domain.betiing.BetAmount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    void 입력받은_배팅금액을_저장한다() {
        BetAmount betAmount = BetAmount.from(1000);
        Assertions.assertThat(betAmount.amount()).isEqualTo(1000);
    }

    @Test
    void 배팅_금액이_음수인_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> BetAmount.from(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
