package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Nested
    class GetProfitTest {

        @Test
        void 블랙잭_승리면_배팅_금액의_1_5배를_반환한다() {
            // given
            int betAmount = 1000;

            // when
            int actual = Result.BLACKJACK_WIN.getProfit(betAmount);

            // then
            assertThat(actual).isEqualTo(1500);
        }

        @Test
        void 일반_승리면_배팅_금액과_같은_수익을_반환한다() {
            // given
            int betAmount = 1000;

            // when
            int actual = Result.WIN.getProfit(betAmount);

            // then
            assertThat(actual).isEqualTo(1000);
        }

        @Test
        void 무승부면_수익은_0이다() {
            // given
            int betAmount = 1000;

            // when
            int actual = Result.DRAW.getProfit(betAmount);

            // then
            assertThat(actual).isEqualTo(0);
        }

        @Test
        void 패배면_배팅_금액만큼_음수_수익을_반환한다() {
            // given
            int betAmount = 1000;

            // when
            int actual = Result.LOSE.getProfit(betAmount);

            // then
            assertThat(actual).isEqualTo(-1000);
        }
    }
}
