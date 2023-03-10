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
    void isMaxScore(int input) {
        Score score = new Score(input);

        assertThat(score.isMaxScore()).isFalse();
    }
}
