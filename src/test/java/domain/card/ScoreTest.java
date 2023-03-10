package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {
    private Score tenScore;

    @BeforeEach
    void setUp() {
        tenScore = new Score(10);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11})
    @DisplayName("매개변수의 스코어보다 이하인 경우 true 반환")
    void isLessThenOrEqualTo(int otherScore) {
        assertThat(tenScore.isLessThenOrEqualTo(new Score(otherScore))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12})
    @DisplayName("매개변수의 스코어보다 미만인 경우 true 반환")
    void isLessThen(int otherScore) {
        assertThat(tenScore.isLessThen(new Score(otherScore))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    @DisplayName("매개변수의 스코어보다 초과인 경우 true 반환")
    void isOverThen(int otherScore) {
        assertThat(tenScore.isOverThen(new Score(otherScore))).isTrue();
    }

    @Test
    @DisplayName("스코어를 더해서 반환한다.")
    void add() {
        Score score = tenScore.add(new Score(11));
        assertThat(score.getScore()).isEqualTo(21);
    }
}