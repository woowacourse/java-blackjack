package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetTest {

    @Test
    void 베팅_금액을_저장한다() {
        Bet bet = new Bet("30000");

        int betAmount = bet.getAmount();

        assertThat(betAmount).isEqualTo(30000);
    }

    @Test
    void 베팅_금액을_숫자가_아닌_값으로_입력하면_예외가_발생한다() {
        assertThatThrownBy(
                () -> new Bet("abc")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 베팅_금액의_단위가_1000이_아니면_예외가_발생한다() {
        assertThatThrownBy(
                () -> new Bet("1500")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 최소_베팅_금액은_1000원이다() {
        assertThatThrownBy(
                () -> new Bet("0")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
