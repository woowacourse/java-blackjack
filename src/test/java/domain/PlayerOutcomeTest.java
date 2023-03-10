package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerOutcomeTest {
    @Nested
    class 점수로승패를판단 {
        @ParameterizedTest
        @CsvSource(value = {"16:LOSE", "17:DRAW", "21:WIN"}, delimiter = ':')
        void should_승패를판단한다_when_버스트가없을때(int playerScore, PlayerOutcome expected) {
            //given
            int dealerScore = 17;

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerScore, dealerScore);

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"21:WIN", "22:LOSE"}, delimiter = ':')
        void should_승패를판단한다_when_버스트일시(int playerScore, PlayerOutcome expected) {
            //given
            int dealerScore = 23;

            //when
            PlayerOutcome actual = PlayerOutcome.of(playerScore, dealerScore);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
