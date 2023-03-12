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

    @Test
    @DisplayName("점수가 11점 이하이면 10을 더한다.")
    void addAdditionalScore() {
        Score score = new Score(10);

        assertThat(score.calculateAceScore().getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("점수가 11점 초과이면 점수가 변하지 않는다.")
    void addAdditionalScoreFail() {
        Score score = new Score(12);

        assertThat(score.calculateAceScore().getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("점수가 16점 이하이면 true를 반환한다.")
    void isUnderScore() {
        Score score = new Score(12);

        assertThat(score.isUnderScore()).isTrue();
    }

    @Test
    @DisplayName("점수가 16점 초과이면 false를 반환한다.")
    void isUnderScoreFalse() {
        Score score = new Score(17);

        assertThat(score.isUnderScore()).isFalse();
    }

    @Test
    @DisplayName("점수가 21점 초과이면 true 반환한다.")
    void isOverMaxScore() {
        Score score = new Score(22);

        assertThat(score.isOverMaxScore()).isTrue();
    }

    @Test
    @DisplayName("비교하는 score보다 점수가 높으면 true를 반환한다.")
    void isBiggerThan() {
        Score score = new Score(11);
        Score score1 = new Score(10);

        assertThat(score.isBiggerThan(score1)).isTrue();
    }

    @Test
    @DisplayName("비교하는 score보다 점수가 높으면 false를 반환한다.")
    void isBiggerThanFalse() {
        Score score = new Score(11);
        Score score1 = new Score(10);

        assertThat(score1.isBiggerThan(score)).isFalse();
    }
}
