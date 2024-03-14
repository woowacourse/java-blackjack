package domain.participant;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {

    @Test
    void Score_인스턴스를_생성한다() {
        // Given
        int totalScore = 20;

        // When
        Score score = new Score(totalScore);

        // Then
        assertThat(score).isNotNull();
    }

    @Test
    void 입력된_총_점수보다_크면_true를_반환한다() {
        // Given
        Score totalScore = new Score(10);

        // When
        boolean isBigger = totalScore.isBigger(new Score(5));

        // Then
        assertThat(isBigger).isTrue();
    }
}
