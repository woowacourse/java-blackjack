package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static domain.player.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BettingMoney 는")
class BettingMoneyTest {

    @Test
    void 금액을_가지고_생성된다() {
        // given
        final int amount = 1000;

        // when
        BettingMoney bettingMoney = BettingMoney.of(amount);

        // then
        assertThat(bettingMoney.amount()).isEqualTo(amount);
    }

    @ParameterizedTest(name = "금액이 1000 원보다 작다면(ex: {0}) 오류가 발생한다.")
    @ValueSource(ints = {0, 500, 800, 999})
    void 금액이_1000원보다_작으면_오류가_발생한다(final int amount) {
        // when & then
        assertThatThrownBy(() -> BettingMoney.of(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "금액이 1000 단위가 아니라면(ex: {0}) 오류가 발생한다.")
    @ValueSource(ints = {1001, 1500, 10500})
    void 금액이_1000단위가_아니라면_오류가_발생한다(final int amount) {
        // when & then
        assertThatThrownBy(() -> BettingMoney.of(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_금액인_경우_동등하다() {
        // given
        final int amount = 1000;
        BettingMoney bettingMoney1 = BettingMoney.of(amount);
        BettingMoney bettingMoney2 = BettingMoney.of(amount);

        // when & then
        assertThat(bettingMoney1).isEqualTo(bettingMoney2);
    }

    @Nested
    class 게임_결과별로_베팅_금액에_대한_수익을_반환한다 {

        @ParameterizedTest(name = "{0}인 경우 {1} 배를 얻는다")
        @ArgumentsSource(ResultAndRateProvider.class)
        void test(final GameResult result, final double rate) {
            // given
            final BettingMoney money = BettingMoney.of(1000);

            // when
            final Revenue revenue = money.revenue(result);

            // then
            assertThat(revenue.amount()).isEqualTo((int) (1000 * rate));
        }

    }

    static class ResultAndRateProvider implements ArgumentsProvider {

        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Named.of("블랙잭 승리", BLACKJACK_WIN), 1.5),
                    Arguments.of(Named.of("승리 ", WIN), 1),
                    Arguments.of(Named.of("무승부", DRAW), 0),
                    Arguments.of(Named.of("패배", LOSE), -1)
            );
        }
    }
}
