package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreTest {

    @Test
    @DisplayName("점수 계산")
    void calculateScore() {
        Score score = new Score();
        score.calculateScore(List.of(Letter.FIVE, Letter.NINE));

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("21점 초과일 때 ACE 처리 (ACE 1장인 경우)")
    void calculateScoreIncludeAce() {
        Score score = new Score();
        score.calculateScore(List.of(Letter.FIVE, Letter.NINE, Letter.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("21점 초과일 때 ACE 처리 (ACE 3장일 경우)")
    void calculateScoreIncludeAce3() {
        Score score = new Score();
        score.calculateScore(List.of(Letter.FIVE, Letter.NINE,
                Letter.ACE, Letter.ACE, Letter.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("ACE 가 4장일 경우")
    void calculateScoreAce4() {
        Score score = new Score();
        score.calculateScore(List.of(Letter.ACE, Letter.ACE, Letter.ACE, Letter.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }
}
