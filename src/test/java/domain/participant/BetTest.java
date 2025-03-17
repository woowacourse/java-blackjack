package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BetTest {

    @Test
    void 베팅금액_범위를_벗어나면_예외가_발생한다() {
        // given
        int amount = 300_000_000;

        // when & then
        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("배팅 금액은 .* ~ .*만 가능합니다.");
    }

    static Stream<Arguments> createCalculateProfit() {
        return Stream.of(
                Arguments.of(1_000_000, 2.0, 1_000_000),
                Arguments.of(10_000, 0, -10_000),
                Arguments.of(1_000_000, 0.3, -700_000)
        );
    }

    @ParameterizedTest
    @MethodSource("createCalculateProfit")
    void 이익_금액을_반환한다(int initMoney, double profitRate, int expected) {
        // given
        Bet bet = new Bet(initMoney);

        // when
        int actual = bet.calculateProfit(profitRate);

        // then
        assertThat(actual).isEqualTo(expected);
    }

}
