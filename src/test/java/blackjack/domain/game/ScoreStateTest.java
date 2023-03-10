package blackjack.domain.game;

import static blackjack.domain.game.ScoreState.of;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreStateTest {

    @ParameterizedTest
    @CsvSource(value = {"15:HIT", "16:HIT", "17:STAY", "21:STAY", "22:BUST"}, delimiter = ':')
    @DisplayName("점수에 맞는 상태를 반환한다")
    void gameStateTest(int score, ScoreState expect) {
        assertThat(of(score)).isEqualTo(expect);
    }
}
