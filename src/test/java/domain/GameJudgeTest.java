package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameJudgeTest {

    @DisplayName("딜러와 플레이어의 점수를 비교해 플레이어의 게임결과를 반환한다.")
    @Nested
    class Judge {

        @DisplayName("둘 다 버스트인 경우")
        @Test
        void bothBust() {
            int dealerPoint = 22;
            int playerPoint = 22;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
        }

        @DisplayName("플레이어만 버스트인 경우")
        @Test
        void playerBust() {
            int dealerPoint = 18;
            int playerPoint = 22;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("딜러만 버스트인 경우")
        @Test
        void dealerBust() {
            int dealerPoint = 22;
            int playerPoint = 18;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("둘다 블랙잭인 경우")
        @Test
        void bothBlackJack() {
            int dealerPoint = 21;
            int playerPoint = 21;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.DRAW);
        }

        @DisplayName("플레이어만 블랙잭인 경우")
        @Test
        void playerBlackJack() {
            int dealerPoint = 20;
            int playerPoint = 21;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("딜러만 블랙잭인 경우")
        @Test
        void dealerBlackJack() {
            int dealerPoint = 21;
            int playerPoint = 20;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("둘 다 블랙잭이나 버스트가 아니고 플레이어가 점수가 더 높은 경우")
        @Test
        void playerWin() {
            int dealerPoint = 19;
            int playerPoint = 20;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("둘 다 블랙잭이나 버스트가 아니고 딜러가 점수가 더 높은 경우")
        @Test
        void playerLose() {
            int dealerPoint = 20;
            int playerPoint = 19;
            GameResult gameResult = GameJudge.judgePlayerWithDealerPointAndPlayerPoint(dealerPoint, playerPoint);
            Assertions.assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }
    }
}
