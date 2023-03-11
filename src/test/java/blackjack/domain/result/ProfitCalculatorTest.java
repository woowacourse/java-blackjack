package blackjack.domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    @Nested
    @DisplayName("플레이어의 게임 결과에 따라 수익을 계산한다.")
    class PlayerProfitTest {

        private final String name = "test";
        private ProfitCalculator profitCalculator;

        @BeforeEach
        void setup() {
            profitCalculator = new ProfitCalculator();
            int betAmount = 1000;

            profitCalculator.bet(name, betAmount);
        }

        @Test
        @DisplayName("블랙잭 승리시 배팅 금액의 1.5배의 수익을 얻는다")
        void blackjackWinTest() {
            final int expectedProfit = 1500;

            final int actualProfit = getActualProfit(GameResult.BLACKJACK_WIN);

            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        @DisplayName("일반 승리시 배팅 금액과 같은 수익을 얻는다")
        void normalWinTest() {
            final int expectedProfit = 1000;

            final int actualProfit = getActualProfit(GameResult.NORMAL_WIN);

            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        @DisplayName("동점시 배팅 금액만 돌려받고 수익을 얻지 못한다")
        void tieTest() {
            final int expectedProfit = 0;

            final int actualProfit = getActualProfit(GameResult.TIE);

            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        @Test
        @DisplayName("패배시 배팅 금액을 잃어 배팅 금액만큼 손해를 본다")
        void loseTest() {
            final int expectedProfit = -1000;

            final int actualProfit = getActualProfit(GameResult.LOSE);

            assertThat(actualProfit).isEqualTo(expectedProfit);
        }

        private int getActualProfit(final GameResult blackjackWin) {
            profitCalculator.putGameResult(name, blackjackWin);
            return profitCalculator.calculateProfit(name);
        }
    }

    @Test
    @DisplayName("플레이어들의 수익 총합을 통해 딜러의 수익을 계산한다")
    void dealerProfitTest() {
        final ProfitCalculator profitCalculator = new ProfitCalculator();
        final List<String> names = List.of("test1", "test2", "test3");
        final List<Integer> betAmounts = List.of(1000, 2000, 5000);
        final List<GameResult> gameResults = List.of(GameResult.TIE, GameResult.LOSE, GameResult.BLACKJACK_WIN);
        final int expectedResult = -5500;

        for (int index = 0; index < names.size(); index++) {
            profitCalculator.bet(names.get(index), betAmounts.get(index));
            profitCalculator.putGameResult(names.get(index), gameResults.get(index));
        }
        final int dealerProfit = profitCalculator.calculateDealerProfit();

        assertThat(dealerProfit).isEqualTo(expectedResult);
    }

}
