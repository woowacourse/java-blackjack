package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetTest {

    @Test
    void 베팅_금액을_숫자가_아닌_값으로_입력하면_예외가_발생한다() {
        assertThatThrownBy(
                () -> new Bet("abc")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
