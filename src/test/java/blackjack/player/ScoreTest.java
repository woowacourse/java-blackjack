package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("점수를 더할 수 있다.")
    void addScoreTest() {
        Score score = new Score(2);

        assertThat(score.add(3)).isEqualTo(new Score(5));
    }

    @Test
    @DisplayName("점수가 버스트인지 판단한다.")
    void isBustTest() {
        Score bustScore = new Score(22);
        Score notBustScore = new Score(21);

        assertAll(
                () -> assertThat(bustScore.isBust()).isTrue(),
                () -> assertThat(notBustScore.isBust()).isFalse()
        );
    }

    @Test
    @DisplayName("점수가 버스트인지 판단한다.")
    void isNotBustTest() {
        Score bustScore = new Score(22);
        Score notBustScore = new Score(21);

        assertAll(
                () -> assertThat(bustScore.isNotBust()).isFalse(),
                () -> assertThat(notBustScore.isNotBust()).isTrue()
        );
    }

    @Test
    @DisplayName("점수의 대소비교가 가능하다.")
    void isLargerThanTest() {
        Score score = new Score(15);

        assertAll(
                () -> assertThat(score.isLargerThan(new Score(14))).isTrue(),
                () -> assertThat(score.isLargerThan(new Score(16))).isFalse()
        );
    }

    @Test
    @DisplayName("점수의 대소비교가 가능하다.")
    void isSmallerThanEqualTest() {
        Score score = new Score(15);

        assertAll(
                () -> assertThat(score.isSmallerThanOrEqualTo(new Score(14))).isFalse(),
                () -> assertThat(score.isSmallerThanOrEqualTo(new Score(16))).isTrue()
        );
    }

    @Test
    @DisplayName("보정된 에이스 점수를 계산한다.")
    void changeToLargeAceTest() {
        Score canChangeScore = new Score(11);
        Score cantChangeScore = new Score(12);

        assertAll(
                () -> assertThat(canChangeScore.changeToLargeAceScore()).isEqualTo(new Score(21)),
                () -> assertThat(cantChangeScore.changeToLargeAceScore()).isEqualTo(new Score(12))
        );
    }
}
