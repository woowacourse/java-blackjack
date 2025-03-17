package blackjack.domain.card;

import static blackjack.domain.GameResult.getMultiplyRatio;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingResultTest {
    @Nested
    class 블랙잭_없는_경우_점수에_따라_베팅금액을_계산할_수_있다 {
        @Test
        void 플레이어_점수가_21을_초과하는_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(22, 3);
            BlackjackScore dealerScore = new BlackjackScore(20, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21을_초과하지_않고_딜러가_21_초과하는_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(25, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_모두_21을_초과하지_않고_플레이어가_점수가_높은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(18, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_모두_21을_초과하지_않고_플레이어가_점수가_낮은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(17, 3);
            BlackjackScore dealerScore = new BlackjackScore(18, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21을_초과하지_않고_딜러가_21인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(20, 3);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }

        @Test
        void 플레이어_점수가_21이고_딜러가_21_초과인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 3);
            BlackjackScore dealerScore = new BlackjackScore(24, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어_점수가_21이고_딜러가_21_미만인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 3);
            BlackjackScore dealerScore = new BlackjackScore(20, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1);
        }

        @Test
        void 플레이어와_딜러가_점수가_같은_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 4);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(0);
        }
    }

    @Nested
    class 블랙잭_포함된_경우_점수에_따라_베팅금액을_계산할_수_있다 {
        @Test
        void 플레이어가_블랙잭이고_딜러가_블랙잭이_아닌_21인_경우() {
            //given
            BlackjackScore playerScore = new BlackjackScore(21, 2);
            BlackjackScore dealerScore = new BlackjackScore(21, 3);

            //when
            double multiplyRatio = getMultiplyRatio(playerScore, dealerScore);

            //then
            assertThat(multiplyRatio).isEqualTo(1.5);
        }
    }
}
