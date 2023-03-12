package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("내 점수가 다른 점수보다 낮거나 같으면 거짓이 반환되어야 한다.")
    void isOverThan_lowerThanOther(int input) {
        // given
        Score myScore = new Score(input);
        Score otherScore = new Score(5);

        // expect
        assertThat(myScore.isWinTo(otherScore))
                .isFalse();
    }

    @Test
    @DisplayName("내 점수가 다른 점수보다 높으면 참이 반환되어야 한다.")
    void isOverThan_overThanOther() {
        // given
        Score myScore = new Score(10);
        Score otherScore = new Score(9);

        // expect
        assertThat(myScore.isWinTo(otherScore))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("상대방의 점수가 bust면 점수에 상관없이 참이 반환되어야 한다.")
    void isOverThan_otherScoreBust(int input) {
        // given
        Score myScore = new Score(input);
        Score otherScore = new Score(22);

        // expect
        assertThat(myScore.isWinTo(otherScore))
                .isTrue();
    }

    @Test
    @DisplayName("나의 점수가 bust면 점수에 상관없이 거짓이 반환되어야 한다.")
    void isOverThan_myScoreBust() {
        // given
        Score myScore = new Score(22);
        Score otherScore = new Score(1);

        // expect
        assertThat(myScore.isWinTo(otherScore))
                .isFalse();
    }
}
