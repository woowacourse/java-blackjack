package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("승패 판단 테스트")
    class PlayerResultTest {

        @Test
        @DisplayName("플레이어의 카드합이 딜러보다 높다면 승리한다.")
        void isWin() {
            int dealerSum = 18;
            int playerSum = 21;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("플레이어의 카드합이 딜러와 같다면 비긴다.")
        void isDraw() {
            int dealerSum = 21;
            int playerSum = 21;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("플레이어의 카드합이 딜러보다 낮다면 패배한다.")
        void isLose() {
            int dealerSum = 21;
            int playerSum = 20;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.LOSE);
        }
    }

    @Nested
    @DisplayName("수익 테스트")
    class ResultProfitTest {

        @Test
        @DisplayName("승리면서 블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void winWithBlackjack() {
            GameResult gameResult = GameResult.WIN;
            int profit = gameResult.calculateProfit(true, 10000);

            assertThat(profit).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void winWithNotBlackjack() {
            GameResult gameResult = GameResult.WIN;
            int profit = gameResult.calculateProfit(false, 10000);

            assertThat(profit).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void draw() {
            GameResult gameResult = GameResult.DRAW;
            int profit = gameResult.calculateProfit(false, 10000);

            assertThat(profit).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void lose() {
            GameResult gameResult = GameResult.LOSE;
            int profit = gameResult.calculateProfit(false, 10000);

            assertThat(profit).isEqualTo(-10000);
        }
    }
}
