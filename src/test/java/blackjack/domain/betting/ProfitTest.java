package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProfitTest {

    @ParameterizedTest(name = "퍼센트: {0}, 원금: {1}, 결과: {2}")
    @CsvSource(value = {"50,10000,15000", "30,10000,13000"})
    void 수익이_퍼센트만큼_증가한다(final int percent, final int value, final int expected) {
        final Profit profit = new Profit(value);

        final Profit increase = profit.increaseByPercent(percent);

        assertThat(increase.getValue()).isEqualTo(expected);
    }

    @Test
    void 수익이_사라진다() {
        final Profit profit = new Profit(10000);

        assertThat(profit.zero().getValue()).isEqualTo(0);
    }

    @Nested
    class loss_메서드는 {

        @Test
        void 수익이_양수인_경우_음수가_된다() {
            final Profit profit = new Profit(10000);

            final Profit reverse = profit.loss();

            assertThat(reverse.getValue()).isEqualTo(-10000);
        }

        @Test
        void 수익이_음수인_경우_유지한다() {
            final Profit profit = new Profit(-10000);

            final Profit reverse = profit.loss();

            assertThat(reverse.getValue()).isEqualTo(-10000);
        }
    }
}
