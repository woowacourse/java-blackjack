package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Profit;
import blackjack.domain.participant.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Nested
    @DisplayName("수익금 계산 테스트")
    class 수익금_계산_테스트 {

        @Test
        @DisplayName("승리한 경우 베팅금만큼 수익이 발생한다.")
        void 승리한_경우_베팅금만큼_수익이_발생한다() {
            Bet bet = new Bet(10000);
            GameResult gameResult = GameResult.WIN;
            int expected = 10000;

            int actual = Profit.calculate(bet, gameResult);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("블랙잭으로 인한 승리를 한 경우 베팅금의 절반만큼 수익이 발생한다.")
        void 블랙잭으로_인한_승리를_한_경우_베팅금의_절반만큼_수익이_발생한다() {
            Bet bet = new Bet(10000);
            GameResult gameResult = GameResult.BLACKJACK;
            int expected = 5000;

            int actual = Profit.calculate(bet, gameResult);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("무승부인 경우 수익이 발생하지 않는다.")
        void 무승부인_경우_수익이_발생하지_않는다() {
            Bet bet = new Bet(10000);
            GameResult gameResult = GameResult.DRAW;
            int expected = 0;

            int actual = Profit.calculate(bet, gameResult);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("패배한 경우 베팅금의 -1배만큼 수익이 발생한다.")
        void 패배한_경우_베팅금의_마이너스만큼_수익이_발생한다() {
            Bet bet = new Bet(10000);
            GameResult gameResult = GameResult.LOSE;
            int expected = -10000;

            int actual = Profit.calculate(bet, gameResult);

            assertThat(actual).isEqualTo(expected);
        }
    }
}
