package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WagerTest {

    @Test
    void 배당률을_이용하여_수익_금액을_반환한다() {
        // given
        Wager wager = new Wager(10_000);
        double profitRate = 0.3;

        // when
        int profit = wager.calculateProfit(profitRate);

        // then
        int expected = -7_000;
        assertThat(profit).isEqualTo(expected);
    }

    @Test
    void 베팅_금액이_음수인_경우_예외가_발생한다() {
        // given
        int betAmount = -1_000;

        // when & then
        assertThatThrownBy(() -> new Wager(betAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

}
