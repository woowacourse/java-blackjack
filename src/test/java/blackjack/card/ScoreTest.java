package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import card.Score;

public class ScoreTest {
    @Test
    @DisplayName("점수가 21점이면 true 를 반환한다.")
    void isMaxScore() {
        Score score = new Score(21);

        assertThat(score.isMaxScore()).isTrue();
    }

    @ParameterizedTest(name = "점수가 21점이 아니면 false 를 반환한다. 입력값 = {0}")
    @ValueSource(ints = {10, 20, 22})
    void isMaxScoreFail(int input) {
        Score score = new Score(input);

        assertThat(score.isMaxScore()).isFalse();
    }

    @ParameterizedTest(name = "점수가 11점이 이하이면 true 를 반환한다. 입력값 = {0}")
    @ValueSource(ints = {10, 9, 2})
    void canAddAdditionalScore(int input) {
        Score score = new Score(input);

        assertThat(score.canAddAdditionalScore()).isTrue();
    }

    @ParameterizedTest(name = "점수가 11점 초과이면 false 를 반환한다. 입력값 = {0}")
    @ValueSource(ints = {12,13,23})
    void canAddAdditionalScoreFalse(int input) {
        Score score = new Score(input);

        assertThat(score.canAddAdditionalScore()).isFalse();
    }
}
