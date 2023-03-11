package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.Betting;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResultTest {

    @Nested
    class calculateProfit_메서드는 {

        @Test
        void WIN_이라면_베팅만큼_수익을_얻는다() {
            final Betting betting = new Betting(10000);
            final Result result = Result.WIN;

            assertThat(result.calculateProfit(betting)).isEqualTo(10000);
        }

        @Test
        void DRAW_라면_수익이_0이_된다() {
            final Betting betting = new Betting(10000);
            final Result result = Result.DRAW;

            assertThat(result.calculateProfit(betting)).isEqualTo(0);
        }

        @Test
        void LOSE_라면_베팅만큼_돈을_잃는다() {
            final Betting betting = new Betting(10000);
            final Result result = Result.LOSE;

            assertThat(result.calculateProfit(betting)).isEqualTo(-10000);
        }
    }
}
