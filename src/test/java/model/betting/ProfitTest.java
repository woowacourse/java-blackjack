package model.betting;

import static org.assertj.core.api.Assertions.assertThat;

import model.judgement.Profit;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    void 수익을_합산할_수_있다() {
        // given
        Profit profit1 = new Profit(1000);
        Profit profit2 = new Profit(2000);

        // when
        Profit total = profit1.add(profit2);

        // then
        assertThat(total).isEqualTo(new Profit(3000));
    }

    @Test
    void 수익의_부호를_반전할_수_있다() {
        // given
        Profit profit = new Profit(1000);

        // when
        Profit negated = profit.negate();

        // then
        assertThat(negated).isEqualTo(new Profit(-1000));
    }

    @Test
    void ZERO는_0원의_수익이다() {
        // when & then
        assertThat(Profit.ZERO).isEqualTo(new Profit(0));
    }
}
