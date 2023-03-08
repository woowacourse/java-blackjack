package domain.game;

import domain.game.BattingManager.Revenue;
import domain.player.Gambler;
import domain.player.GamblerCompeteResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static domain.fixture.CardAreaFixture.*;
import static domain.fixture.GamblerFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BattingManager 은")
class BattingManagerTest {

    private final Gambler 말랑 = 말랑(equal16CardArea());
    private final Gambler 코다 = 코다(equal18CardArea());
    private final Gambler 콩떡 = 콩떡(equal17CardArea());

    @Test
    void 갬블러와_배팅금액을_받아_생성된다() {
        // given
        Map<Gambler, BattingMoney> battingMoneyMap = new HashMap<>();
        battingMoneyMap.put(말랑, BattingMoney.of(10000));
        battingMoneyMap.put(코다, BattingMoney.of(20000));

        // when & then
        Assertions.assertDoesNotThrow(() -> new BattingManager(battingMoneyMap));
    }

    @Test
    void 갬블러의_결과를_통해_수익을_결정할_수_있다() {
        // given
        Map<Gambler, BattingMoney> battingMoneyMap = new HashMap<>();
        final BattingMoney 만원 = BattingMoney.of(10000);
        final BattingMoney 이만원 = BattingMoney.of(20000);
        battingMoneyMap.put(말랑, 만원);
        battingMoneyMap.put(코다, 이만원);
        battingMoneyMap.put(콩떡, 만원);
        final BattingManager battingManager = new BattingManager(battingMoneyMap);

        Map<Gambler, GamblerCompeteResult> competeResultMap = new HashMap<>();
        competeResultMap.put(말랑, GamblerCompeteResult.LOSE);
        competeResultMap.put(코다, GamblerCompeteResult.WIN);
        competeResultMap.put(콩떡, GamblerCompeteResult.DRAW);

        // when
        final Map<Gambler, Revenue> revenue = battingManager.revenue(competeResultMap);

        // then
        assertAll(
                () -> assertThat(revenue.get(말랑)).isEqualTo(Revenue.lose(만원)),
                () -> assertThat(revenue.get(코다)).isEqualTo(Revenue.defaultWin(이만원)),
                () -> assertThat(revenue.get(콩떡)).isEqualTo(Revenue.draw())
        );
    }

    @Nested
    @DisplayName("Revenue 테스트")
    class RevenueTest {

        @ParameterizedTest(name = "{0} 원을 배팅한 후 진다면 수익은 {1} 원이 된다.")
        @CsvSource({
                "0, 0",
                "10, -10",
                "50000, -50000",
        })
        void lose_시_수익은_기존_배팅_금액만큼_차감된_값이다(final int battingAmount, final int revenueAmount) {
            // given
            final BattingMoney money = BattingMoney.of(battingAmount);

            // when
            final Revenue lose = Revenue.lose(money);

            // then
            assertThat(lose.amount()).isEqualTo(revenueAmount);
        }

        @Test
        void draw_시_수익은_0원이다() {
            // when
            final Revenue draw = Revenue.draw();

            // then
            assertThat(draw.amount()).isEqualTo(0);
        }

        @ParameterizedTest(name = "이긴 경우 블랙잭으로 이긴 것이 아니라면, 자신이 배팅한 금액만큼을 수익으로 얻는다. 예를 들어 {0}원을 걸고 이겼다면 수익은 {1}원이다")
        @CsvSource({
                "0, 0",
                "10, 10",
                "10000, 10000",
        })
        void 이긴_경우_블랙잭이_아니라면_수익은_배팅한_금액과_같다(final int battingAmount, final int revenueAmount) {
            // given
            final BattingMoney money = BattingMoney.of(battingAmount);

            // when
            final Revenue defaultWin = Revenue.defaultWin(money);

            // then
            assertThat(defaultWin.amount()).isEqualTo(revenueAmount);
        }

        @ParameterizedTest(name = "이긴 경우 블랙잭이라면, 자신이 배팅한 금액의 1.5배를 수익으로 얻는다. 예를 들어 {0}원을 걸고 블랙잭으로 이겼다면 수익은 {1}원이다")
        @CsvSource({
                "0, 0",
                "10, 15",
                "10000, 15000",
        })
        void 이긴_경우_블랙잭이라면_수익은_배팅한_금액의_일점오배와_같다(final int battingAmount, final int revenueAmount) {
            // given
            final BattingMoney money = BattingMoney.of(battingAmount);

            // when
            final Revenue blackJackWin = Revenue.blackJackWin(money);

            // then
            assertThat(blackJackWin.amount()).isEqualTo(revenueAmount);
        }
    }
}