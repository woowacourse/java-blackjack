package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {

    @DisplayName("정적 팩터리")
    @Test
    void calculateWinPlayerMoreThanDealer() {
        Score bust = new Score(22);
        Score twentyOne = new Score(21);
        Score twenty = new Score(20);
        Score nineteen = new Score(19);

        assertThat(Outcome.from(twenty, nineteen)).isEqualTo(Outcome.PLAYER_WIN);
        assertThat(Outcome.from(bust, twentyOne)).isEqualTo(Outcome.PLAYER_LOSE);

        assertThat(Outcome.from(twenty, twenty)).isEqualTo(Outcome.PLAYER_DRAW);
        assertThat(Outcome.from(bust, bust)).isEqualTo(Outcome.PLAYER_LOSE);

        assertThat(Outcome.from(twenty, twentyOne)).isEqualTo(Outcome.PLAYER_LOSE);
        assertThat(Outcome.from(twenty, bust)).isEqualTo(Outcome.PLAYER_WIN);
    }
}