package blackjack.game.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.game.GameResult;
import blackjack.user.player.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {

    @Nested
    @DisplayName("배팅 금액 입력 테스트")
    class CreateBetAmountTest {

        @ParameterizedTest
        @ValueSource(ints = {10000, 10001, 9999999, 10000000})
        @DisplayName("원금은 1만원에서 1000만원까지 입력할 수 있다.")
        void createWallet(int principal) {
            BetAmount betAmount = BetAmount.initAmount(principal);

            assertThat(betAmount).isInstanceOf(BetAmount.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 999, 10000001})
        @DisplayName("원금이 1만원미만 1000만원초과라면 입력할 수 없다.")
        void notCreateWallet(int principal) {
            assertThatThrownBy(() -> BetAmount.initAmount(principal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }

    @Nested
    @DisplayName("배팅 금액 계산 테스트")
    class ProfitTest {

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void calculateBlackjackProfit() {
            BetAmount initialBetAmount = BetAmount.initAmount(10000);
            GameResult gameResult = GameResult.WIN;
            boolean isBlackjack = true;

            BetAmount resultBetAmount = initialBetAmount.calculateProfit(gameResult, isBlackjack);
            assertThat(resultBetAmount.getProfit()).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void calculateJustWinProfit() {
            BetAmount initialBetAmount = BetAmount.initAmount(10000);
            GameResult gameResult = GameResult.WIN;
            boolean isBlackjack = false;

            BetAmount resultBetAmount = initialBetAmount.calculateProfit(gameResult, isBlackjack);
            assertThat(resultBetAmount.getProfit()).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void calculateDrawProfit() {
            BetAmount initialBetAmount = BetAmount.initAmount(10000);
            GameResult gameResult = GameResult.DRAW;
            boolean isBlackjack = false;

            BetAmount resultBetAmount = initialBetAmount.calculateProfit(gameResult, isBlackjack);
            assertThat(resultBetAmount.getProfit()).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void calculateLoseProfit() {
            BetAmount initialBetAmount = BetAmount.initAmount(10000);
            GameResult gameResult = GameResult.LOSE;
            boolean isBlackjack = false;

            BetAmount resultBetAmount = initialBetAmount.calculateProfit(gameResult, isBlackjack);
            assertThat(resultBetAmount.getProfit()).isEqualTo(-10000);
        }
    }
}
