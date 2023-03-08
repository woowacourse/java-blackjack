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

    @ParameterizedTest(name = "원금: {0}, 결과: {1}")
    @CsvSource(value = {"10000,15000", "13333,19999"})
    void 수익이_50_퍼센트_증가한다(final int value, final int expected) {
        final Profit profit = new Profit(value);

        final Profit increase = profit.increaseFiftyPercent();

        assertThat(increase.getValue()).isEqualTo(expected);
    }

    @Nested
    class reverse_메서드는 {

        @Test
        void 수익이_양수인_경우_음수가_된다() {
            final Profit profit = new Profit(10000);

            final Profit reverse = profit.reverse();

            assertThat(reverse.getValue()).isEqualTo(-10000);
        }

        @Test
        void 수익이_음수인_경우_양수가_된다() {
            final Profit profit = new Profit(-10000);

            final Profit reverse = profit.reverse();

            assertThat(reverse.getValue()).isEqualTo(10000);
        }
    }
}
