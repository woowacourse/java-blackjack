package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_플레이어의_승패를_비교한다() {
        // given
        int participantScore = 20;
        int dealerScore = 15;

        Referee referee = new Referee();

        // when
        GameResult result = referee.judge(dealerScore, participantScore);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }
}
