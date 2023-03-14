package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameOutcomeTest {
    @Nested
    class 점수로승패를판단 {
        @ParameterizedTest
        @CsvSource(value = {"16:LOSE", "17:DRAW", "21:WIN"}, delimiter = ':')
        void should_승패를통해수익을계산한다_when_버스트가없을때(int playerScore, GameOutcome expected) {
            //given
            int dealerScore = 17;

            //when
            GameOutcome actual = GameOutcome.of(playerScore, dealerScore, 3);

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"21:WIN", "22:LOSE"}, delimiter = ':')
        void should_승패를통해수익을계산한다_when_버스트일시(int playerScore, GameOutcome expected) {
            //given
            int dealerScore = 23;

            //when
            GameOutcome actual = GameOutcome.of(playerScore, dealerScore, 3);

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Nested
        class 수익계산 {
            @ParameterizedTest
            @CsvSource(value = {"20:1", "18:0", "17:-1"}, delimiter = ':')
            void should_수익을계산한다_when_플레이어가블랙잭이아닐때(int playerScore, int ratio) {
                //given
                int dealerScore = 18;
                GameOutcome gameOutcome = GameOutcome.of(playerScore, dealerScore, 2);

                //when
                int revenue = gameOutcome.calculateRevenue(1000);

                //then
                assertThat(revenue).isEqualTo(1000 * ratio);
            }

            @ParameterizedTest
            @CsvSource(value = {"21:0", "18:1.5"}, delimiter = ':')
            void should_수익을계산한다_when_플레이어가블랙잭일때(int dealerScore, double ratio) {
                //given
                int playerScore = 21;
                GameOutcome gameOutcome = GameOutcome.of(playerScore, dealerScore, 2);

                //when
                int revenue = gameOutcome.calculateRevenue(1000);

                //then
                assertThat(revenue).isEqualTo((int) (1000 * ratio));
            }
        }
    }
}
